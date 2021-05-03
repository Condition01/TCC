package br.com.ifeira.compra.handlers;

import br.com.ifeira.compra.ComprasApplication;
import br.com.ifeira.compra.entity.Pagamento;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class HandlerCriptografia extends PagamentoInBaseHandler {

    @Override
    public PagamentoDTO handle(Pagamento pagamento) throws Exception {
        if(this.getNext() != null) {
            PublicKey publicKey = readPublicKey(ComprasApplication.RESOURCES_DIR + "public.der");

            pagamento.setNumeroCartao(new String(encrypt(publicKey,pagamento.getNumeroCartao().getBytes()), StandardCharsets.ISO_8859_1));
            pagamento.setCvv(new String(encrypt(publicKey,pagamento.getCvv().getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1));
            pagamento.setValidadeCartao(new String(encrypt(publicKey,pagamento.getValidadeCartao().getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1));

            return this.getNext().handle(pagamento);
        }
        return null;
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
