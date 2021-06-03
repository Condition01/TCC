package br.com.ifeira.pagamento.handlers;

import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;

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
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;

public class HandlerDecriptacao extends PagamentoOutBaseHandler {
    @Override
    public PagamentoDTO handle(PagamentoDTO pagamento) throws Exception {
        try {
            PrivateKey privateKey = readPrivateKey("./keys/private.key");
            pagamento.setNumeroCartao(new String(decrypt(privateKey, pagamento.getNumeroCartao().getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1));
            pagamento.setCvv(new String(decrypt(privateKey, pagamento.getCvv().getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1));
            pagamento.setValidadeCartao(new String(decrypt(privateKey, pagamento.getValidadeCartao().getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1));
            if (this.getNext() != null) {
                return this.getNext().handle(pagamento);
            }
            return pagamento;
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | IllegalBlockSizeException | BadPaddingException e) {
            throw new Exception("Erro na decriptação");
        }
    }

    public byte[] readFileBytes(String filename) throws IOException {
        Path path = Paths.get(filename);
        return Files.readAllBytes(path);
    }

    public PrivateKey readPrivateKey(String filePath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, ClassNotFoundException {
        ObjectInputStream privateKeyStream = new ObjectInputStream(new FileInputStream(filePath));
        return (PrivateKey) privateKeyStream.readObject();
    }

    public byte[] decrypt(PrivateKey key, byte[] ciphertext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(ciphertext);
    }

}



