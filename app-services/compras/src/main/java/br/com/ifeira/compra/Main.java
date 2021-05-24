package br.com.ifeira.compra;

import org.apache.commons.lang.time.DateUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException, IOException, NoSuchAlgorithmException {
//        genKey()
        Date truncatedDate = DateUtils.truncate(new Date(), Calendar.DATE);
        System.out.println(truncatedDate);
    }

    private static void genKey() throws NoSuchAlgorithmException, IOException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair key = keyGen.genKeyPair();

        File publicKeyFile = new File("./keys/public.key");
        File privateKeyFile = new File("./keys/private.key");

        if (publicKeyFile.getParentFile() != null) {
            publicKeyFile.getParentFile().mkdirs();
        }

        publicKeyFile.createNewFile();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(publicKeyFile));
        objectOutputStream.writeObject(key.getPublic());
        objectOutputStream.close();

        ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(new FileOutputStream(privateKeyFile));
        objectOutputStream1.writeObject(key.getPrivate());
        objectOutputStream1.close();
    }
}
