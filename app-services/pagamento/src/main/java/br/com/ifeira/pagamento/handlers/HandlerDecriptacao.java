package br.com.ifeira.pagamento.handlers;

import br.com.ifeira.pagamento.PagamentoApplication;
import br.com.ifeira.pagamento.entity.PagamentoResponse;
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
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class HandlerDecriptacao extends PagamentoOutBaseHandler {
    @Override
    public PagamentoResponse handle(PagamentoDTO pagamento) throws Exception {
        try {
            if (this.getNext() != null) {
                PrivateKey privateKey = readPrivateKey(PagamentoApplication.RESOURCES_DIR + "private.der");
                pagamento.setNumeroCartao(new String(decrypt(privateKey, pagamento.getNumeroCartao().getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1));
                pagamento.setCvv(new String(decrypt(privateKey, pagamento.getCvv().getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1));
                pagamento.setValidadeCartao(new String(decrypt(privateKey, pagamento.getValidadeCartao().getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1));
                return this.getNext().handle(pagamento);
            }
            return null;

        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | IllegalBlockSizeException | BadPaddingException e) {
            throw new Exception("Erro na decriptação");
        } catch (Exception e1) {
            throw new Exception(e1.getLocalizedMessage());
        }
    }

    public byte[] readFileBytes(String filename) throws IOException {
        Path path = Paths.get(filename);
        return Files.readAllBytes(path);
    }

    public PrivateKey readPrivateKey(String filename) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(readFileBytes(filename));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    public byte[] decrypt(PrivateKey key, byte[] ciphertext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(ciphertext);
    }

}



