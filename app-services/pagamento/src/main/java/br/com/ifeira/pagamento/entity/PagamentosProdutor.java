package br.com.ifeira.pagamento.entity;

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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

@Component
public class PagamentosProdutor {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private QueueConfig config;

    public void enfileirarPagamentosConcluidos(PagamentoDTO pagamento) throws Exception {
        limparDadosPag(pagamento);

        this.rabbitTemplate.convertAndSend(
                this.config.PAGAMENTOS_CONCLUIDOS_TOPIC_EXCHANGE_NAME,
                this.config.PAGAMENTOS_CONCLUIDOS_KEY_NAME,
                pagamento);
    }

    public void enfileirarPagamentosComErro(PagamentoDTO pagamento) throws Exception {
        encriptarPagamentos(pagamento);

        this.rabbitTemplate.convertAndSend(
                this.config.PAGAMENTOS_PENDENTES_TOPIC_EXCHANGE_NAME,
                this.config.PAGAMENTOS_PENDENTES_KEY_NAME,
                pagamento, new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        message.getMessageProperties().setHeader("x-delay", "3600000");
                        return message;
                    }
                });
    }

    public void limparDadosPag(PagamentoDTO pagamento) {
        pagamento.setNumeroCartao("");
        pagamento.setCvv("");
        pagamento.setValidadeCartao("");
        pagamento.setNomeCartao("");
        pagamento.setCredId("");
    }

    public void encriptarPagamentos(PagamentoDTO pagamento) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, ClassNotFoundException {
        PublicKey publicKey = readPublicKey("./keys/public.key");

        pagamento.setNumeroCartao(new String(encrypt(publicKey,pagamento.getNumeroCartao().getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1));
        pagamento.setCvv(new String(encrypt(publicKey, pagamento.getCvv().getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1));
        pagamento.setValidadeCartao(new String(encrypt(publicKey, pagamento.getValidadeCartao().getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1));
    }


    public byte[] readFileBytes(String filename) throws IOException {
        Path path = Paths.get(filename);
        return Files.readAllBytes(path);
    }

    public PublicKey readPublicKey(String filePath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, ClassNotFoundException {
        ObjectInputStream publicKeyStream =  new ObjectInputStream(new FileInputStream(filePath));
        return (PublicKey) publicKeyStream.readObject();
    }

    public byte[] encrypt(PublicKey key, byte[] plaintext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(plaintext);
    }

}
