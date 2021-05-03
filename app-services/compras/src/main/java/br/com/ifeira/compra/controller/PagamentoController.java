package br.com.ifeira.compra.controller;

import br.com.ifeira.compra.config.APIConfig;
import br.com.ifeira.compra.config.MailingConfig;
import br.com.ifeira.compra.dao.PagamentoDAO;
import br.com.ifeira.compra.entity.Pagamento;
import br.com.ifeira.compra.factories.PagamentoInConcretHandlerFactory;
import br.com.ifeira.compra.factories.PagamentoInHandlerFactory;
import br.com.ifeira.compra.handlers.PagamentoInHandler;
import br.com.ifeira.compra.shared.dao.Persistivel;
import br.com.ifeira.compra.shared.entity.Carrinho;
import br.com.ifeira.compra.shared.utils.Notificavel;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class PagamentoController {

    @Autowired
    private APIConfig apiConfig;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MailingConfig mailingConfig;
    private Persistivel<Pagamento, Long> pagamentoDAO;
    private Notificavel notificador;
    private PagamentoInHandlerFactory pagFactory;
    private Logger logger = LoggerFactory.getLogger(PagamentoController.class);

    public PagamentoController(@Autowired JdbcTemplate jdbcTemplate) {
        this.pagamentoDAO = new PagamentoDAO(jdbcTemplate);
        this.pagFactory = new PagamentoInConcretHandlerFactory();

//        Properties properties = new Properties();
//        properties.put("mail.smtp.auth", this.mailingConfig.AUTH);
//        properties.put("mail.smtp.starttls.enable", this.mailingConfig.STARTTLS);
//        properties.put("mail.smtp.host", this.mailingConfig.HOST);
//        properties.put("mail.smtp.port", this.mailingConfig.PORT);
//
//        notificador = new NotificacaoEmail(properties, this.mailingConfig.ACCOUNT, this.mailingConfig.PASSWORD);
    }

    public PagamentoDTO enfileirarPagamento(Pagamento pagamento) throws Exception {
        PagamentoInHandler pagChain = this.pagFactory.criarPagamentoInChain();
        String token = pegarTokenAutorizacaoAPIExterna();
        pagamento.setCreditCardId(tokenizarCartao(pagamento.getCreditCardHash(), token));
        PagamentoDTO pagDTO = pagChain.handle(pagamento);
        notificar(pagDTO);
        return pagDTO;
    }

    public Pagamento gerarPagamento(Carrinho carrinho){
        return null;
    }

    private String tokenizarCartao(String creditCardHash, String token) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("X-Api-Version", this.apiConfig.API_VERSION);
        headers.add("X-Resource-Token", this.apiConfig.PRIVATE_TOKEN);
        headers.add("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(creditCardHash, headers);

        ResponseEntity<String> response =
                this.restTemplate.exchange(apiConfig.OAUTH_URL,
                        HttpMethod.POST,
                        entity,
                        String.class);

        JsonNode jsonObjResp = new ObjectMapper().readTree(response.getBody());
        return jsonObjResp.get("creditCardId").asText();
    }

    public String pegarTokenAutorizacaoAPIExterna() throws IOException {
        String preEncodedCliendAndSecret = this.apiConfig.API_ID + ":" + this.apiConfig.API_SECRET;
        String basicAuth = new String(Base64.getEncoder().encode(preEncodedCliendAndSecret.getBytes(StandardCharsets.UTF_8)));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + basicAuth);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", apiConfig.API_GRANT_TYPE);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<String> response =
                this.restTemplate.exchange(apiConfig.OAUTH_URL,
                        HttpMethod.POST,
                        entity,
                        String.class);

        JsonNode jsonObjResp = new ObjectMapper().readTree(response.getBody());

        return jsonObjResp.get("access_token").asText();
    }

    public void notificar(PagamentoDTO pagamentoDTO){

    }

}
