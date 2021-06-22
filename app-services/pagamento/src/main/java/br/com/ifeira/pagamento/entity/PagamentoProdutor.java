package br.com.ifeira.pagamento.entity;

import br.com.ifeira.pagamento.config.QueueConfig;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

@Component
public class PagamentoProdutor {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private QueueConfig config;
    @Value("${mq[0].timeout}")
    private String timeout;

    public void limparDadosPag(PagamentoDTO pagamento) {
        pagamento.setNumeroCartao("");
        pagamento.setCvv("");
        pagamento.setValidadeCartao("");
        pagamento.setNomeCartao("");
        pagamento.setCredId("");
    }
    public void encriptarPagamentos(PagamentoDTO pagamento) throws NoSuchAlgorithmException, IOException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, ClassNotFoundException {
        PublicKey publicKey = readPublicKey("./keys/public.key");

        pagamento.setNumeroCartao(new String(encrypt(publicKey,pagamento.getNumeroCartao().getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1));
        pagamento.setCvv(new String(encrypt(publicKey, pagamento.getCvv().getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1));
        pagamento.setValidadeCartao(new String(encrypt(publicKey, pagamento.getValidadeCartao().getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1));
    }
    public PublicKey readPublicKey(String filePath) throws IOException, ClassNotFoundException {
        ObjectInputStream publicKeyStream =  new ObjectInputStream(new FileInputStream(filePath));
        return (PublicKey) publicKeyStream.readObject();
    }
    public byte[] encrypt(PublicKey key, byte[] plaintext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(plaintext);
    }

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
                        message.getMessageProperties().setHeader("x-delay", timeout);
                        return message;
                    }
                });
    }

}
