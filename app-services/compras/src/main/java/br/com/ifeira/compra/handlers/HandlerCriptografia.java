package br.com.ifeira.compra.handlers;

import br.com.ifeira.compra.entity.Pagamento;

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

public class HandlerCriptografia extends PagamentoInBaseHandler {

    @Override
    public Pagamento handle(Pagamento pagamento) throws Exception {
        PublicKey publicKey = readPublicKey("./keys/public.key");

        pagamento.setNumeroCartao(new String(encrypt(publicKey,
                pagamento.getNumeroCartao().getBytes(StandardCharsets.ISO_8859_1)),
                StandardCharsets.ISO_8859_1));
        pagamento.setCvv(new String(encrypt(publicKey,
                pagamento.getCvv().getBytes(StandardCharsets.ISO_8859_1)),
                StandardCharsets.ISO_8859_1));
        pagamento.setValidadeCartao(new String(encrypt(publicKey,
                pagamento.getValidadeCartao().getBytes(StandardCharsets.ISO_8859_1)),
                StandardCharsets.ISO_8859_1));

        if(this.getNext() != null) {
            return this.getNext().handle(pagamento);
        }
        return pagamento;
    }

    public PublicKey readPublicKey(String filePath) throws IOException, ClassNotFoundException {
        ObjectInputStream publicKeyStream =  new ObjectInputStream(new FileInputStream(filePath));
        return (PublicKey) publicKeyStream.readObject();
    }

    public byte[] encrypt(PublicKey key, byte[] plaintext) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(plaintext);
    }

}
