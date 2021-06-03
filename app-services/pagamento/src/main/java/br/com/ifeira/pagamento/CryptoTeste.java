package br.com.ifeira.pagamento;

import br.com.ifeira.compra.shared.utils.NotificacaoEmail;
import br.com.ifeira.compra.shared.utils.Notificavel;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;

public class CryptoTeste {

    public static void main(String[] args) {
        sendMail();
    }

    public static void sendMail() {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Notificavel notificador = new NotificacaoEmail(properties, "ifeiranotificacao@gmail.com", "123456Aa@");

        notificador.enviarNotificacao("Teste de integração com SERVIDOR SMTP para envio de notificações por email", "thiagoyukiox@hotmail.com", "TESTE DO IFEIRA EMAIL");
    }

    public static void handlePagamento() {
        try {
            PublicKey publicKey = readPublicKey("keys/public.der");
//            PrivateKey privateKey = readPrivateKey("keys/private.der");
//            byte[] message = "Meu NOME!!!!".getBytes("ISO-8859-1");
//            byte[] secret = encrypt(publicKey, message);
//            String myMessage = new String(secret, "ISO-8859-1");
//            byte[] recovered_message = decrypt(privateKey, myMessage.getBytes("ISO-8859-1"));
//            System.out.println(new String(recovered_message, "ISO-8859-1"));


//            PagamentoOutHandlerFactory pagFacotory = new PagamentoOutConcretHandlerFactory();
//            PagamentoOutHandler pagChain = pagFacotory.criarPagamentoOutChain(new APIConfig("", "", "", "", "", "", "", ""), new RestTemplate(), new JdbcTemplate());
//
//            PagamentoDTO pagamentoDTO = new PagamentoDTO();
//            pagamentoDTO.setNumeroCartao(new String(encrypt(publicKey, "12312313".getBytes()), StandardCharsets.ISO_8859_1));
//            pagamentoDTO.setCvv(new String(encrypt(publicKey, "123".getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1));
//            pagamentoDTO.setValidadeCartao(new String(encrypt(publicKey, "20/03".getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1));
//
//            pagChain.handle(pagamentoDTO);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public static byte[] readFileBytes(String filename) throws IOException {
        Path path = Paths.get(filename);
        return Files.readAllBytes(path);
    }

    public static PublicKey readPublicKey(String filename) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(readFileBytes(filename));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(publicSpec);
    }

    public static PrivateKey readPrivateKey(String filename) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(readFileBytes(filename));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    public static byte[] encrypt(PublicKey key, byte[] plaintext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(plaintext);
    }

    public static byte[] decrypt(PrivateKey key, byte[] ciphertext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(ciphertext);
    }
}
