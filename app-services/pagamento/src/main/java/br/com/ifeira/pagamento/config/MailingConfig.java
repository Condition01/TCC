package br.com.ifeira.pagamento.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailingConfig {

    public final String AUTH;
    public final String STARTTLS;
    public final String HOST;
    public final String PORT;
    public final String ACCOUNT;
    public final String PASSWORD;

    public MailingConfig(@Value("${mail.smtp.auth}") String AUTH,
                         @Value("${mail.smtp.starttls.enable}") String STARTTLS,
                         @Value("${mail.smtp.host}") String HOST,
                         @Value("${mail.smtp.port}") String PORT,
                         @Value("${mail.myAccountEmail}") String ACCOUNT,
                         @Value("${mail.password}") String PASSWORD) {
        this.AUTH = AUTH;
        this.STARTTLS = STARTTLS;
        this.HOST = HOST;
        this.PORT = PORT;
        this.ACCOUNT = ACCOUNT;
        this.PASSWORD = PASSWORD;
    }

}
