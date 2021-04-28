package br.com.ifeira.pagamento.config;

import br.com.ifeira.pagamento.handlers.ReqErrorHandler;
import br.com.ifeira.pagamento.handlers.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class APIConfig {

    public final String API_URL;
    public final String API_ID;
    public final String API_SECRET;
    public final String API_GRANT_TYPE;
    public final String PRIVATE_TOKEN;
    public final String PUBLIC_TOKEN;
    public final String OAUTH_URL;
    public final String API_VERSION;

    public APIConfig(@Value("${api-pagamento.url}") String API_URL,
                     @Value("${api-pagamento.oauth.id}") String API_ID,
                     @Value("${api-pagamento.oauth.secret}") String API_SECRET,
                     @Value("${api-pagamento.oauth.grant_type}") String API_GRANT_TYPE,
                     @Value("${api-pagamento.private_token}") String PRIVATE_TOKEN,
                     @Value("${api-pagamento.public_token}") String PUBLIC_TOKEN,
                     @Value("${api-pagamento.oauth.oauth_url}") String OAUTH_URL,
                     @Value("${api-pagamento.api_version}") String API_VERSION) {
        this.API_URL = API_URL;
        this.API_ID = API_ID;
        this.API_SECRET = API_SECRET;
        this.API_GRANT_TYPE = API_GRANT_TYPE;
        this.PRIVATE_TOKEN = PRIVATE_TOKEN;
        this.PUBLIC_TOKEN = PUBLIC_TOKEN;
        this.OAUTH_URL = OAUTH_URL;
        this.API_VERSION = API_VERSION;
    }

    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate rTemplate = new RestTemplate();
        rTemplate.setErrorHandler(new ReqErrorHandler());
        rTemplate.setInterceptors(Collections.singletonList(new RequestInterceptor()));
        return rTemplate;
    }

}
