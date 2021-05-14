package br.com.ifeira.pagamento.entity;

import br.com.ifeira.pagamento.PagamentoApplication;
import br.com.ifeira.pagamento.config.QueueConfig;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

@Component
public class PagamentosProdutor {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private QueueConfig config;

    public void enfileirarPagamentosConcluidos(PagamentoDTO pagamento) throws Exception {
        encryptarPagamentos(pagamento);

        this.rabbitTemplate.convertAndSend(
                this.config.PAGAMENTOS_CONCLUIDOS_TOPIC_EXCHANGE_NAME,
                this.config.PAGAMENTOS_CONCLUIDOS_KEY_NAME,
                pagamento);
    }

    public void enfileirarPagamentosComErro(PagamentoDTO pagamento) throws Exception {
        encryptarPagamentos(pagamento);

        this.rabbitTemplate.convertAndSend(
                this.config.PAGAMENTOS_PENDENTES_TOPIC_EXCHANGE_NAME,
                this.config.PAGAMENTOS_PENDENTES_KEY_NAME,
                pagamento, new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        message.getMessageProperties().setHeader("x-delay", "1");
                        return message;
                    }
                });
    }

    public void encryptarPagamentos(PagamentoDTO pagamento) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
        PublicKey publicKey = readPublicKey(PagamentoApplication.RESOURCES_DIR + "public.der");

        pagamento.setNumeroCartao(new String(encrypt(publicKey,pagamento.getNumeroCartao().getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1));
        pagamento.setCvv(new String(encrypt(publicKey, pagamento.getCvv().getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1));
        pagamento.setValidadeCartao(new String(encrypt(publicKey, pagamento.getValidadeCartao().getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1));
    }

    public byte[] readFileBytes(String filename) throws IOException {
        Path path = Paths.get(filename);
        return Files.readAllBytes(path);
    }

    public PublicKey readPublicKey(String filename) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(readFileBytes(filename));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(publicSpec);
    }

    public byte[] encrypt(PublicKey key, byte[] plaintext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(plaintext);
    }



}
