package br.com.ifeira.compra.shared.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class NotificacaoEmail implements Notificavel{

    private Properties propriedadesMail;
    private String myAccountEmail;
    private String password;

    public NotificacaoEmail(Properties properties, String account, String password) {
        this.propriedadesMail = properties;
        this.myAccountEmail = account;
        this.password = password;
    }

    @Override
    public void enviarNotificacao(String mensagem, String remetente, String titulo) {
        try {
            Session session = Session.getInstance(propriedadesMail, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myAccountEmail, password);
                }
            });

            Message message = prepareMessage(session, remetente, titulo, mensagem);

            Transport.send(message);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public Message prepareMessage(Session session, String remetente, String titulo, String mensagem) {
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(remetente));
            message.setSubject(titulo);
            message.setText(mensagem);
            return message;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
