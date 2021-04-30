package br.com.ifeira.compra.controller;

import br.com.ifeira.compra.config.APIConfig;
import br.com.ifeira.compra.entity.juno.JunoChargeReq;
import br.com.ifeira.compra.shared.dao.PedidoDAO;
import br.com.ifeira.compra.shared.dao.Persistivel;
import br.com.ifeira.compra.shared.entity.Carrinho;
import br.com.ifeira.compra.shared.entity.Cupom;
import br.com.ifeira.compra.shared.entity.Pedido;
import br.com.ifeira.compra.shared.entity.Pessoa;
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
public class PedidoController {

    @Autowired
    private APIConfig apiConfig;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Persistivel<Pedido, Long> pedidoDAO;

    public PedidoController(@Autowired JdbcTemplate jdbcTemplate) {
        this.pedidoDAO = new PedidoDAO(jdbcTemplate);
    }

    private Logger logger = LoggerFactory.getLogger(PedidoController.class);

    public Pedido fecharPedido(Carrinho carrinho, Cupom cupom){
        return null;
    }

    //TODO - EXPURGAR VULNERABILIDADE (RECEBER PESSOA COMO PARAMETRO)
    private String gerarCobranca(Double valor, Pessoa pessoa, String tpPagamento) {
        try {
            String token = pegarTokenAutorizacaoAPIExterna();
            return enviarReqCobranca(valor, tpPagamento, token);
        }catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public boolean cancelarPedido(int numeroPedido){
        return false;
    }

    public String pegarTokenAutorizacaoAPIExterna() throws IOException {
        RestTemplate restTemplate = new RestTemplate();

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

    private String enviarReqCobranca(Double valor, String tpCobranca, String token) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("X-Api-Version", this.apiConfig.API_VERSION);
        headers.add("X-Resource-Token", this.apiConfig.PRIVATE_TOKEN);
        headers.add("Authorization", "Bearer " + token);

        //TODO CRIAR OBJETO DE REQUISIÇÃO PARA RECEBER COBRANÇA

        JunoChargeReq junoChargeReq = new JunoChargeReq();

        HttpEntity<JunoChargeReq> entity = new HttpEntity<>(junoChargeReq, headers);

        ResponseEntity<String> response = this.restTemplate.postForEntity(this.apiConfig.API_URL, entity, String.class);

        JsonNode jsonObjResp = new ObjectMapper().readTree(response.getBody());
        return jsonObjResp.get("_embedded").get("charges").get(1).get("id").asText();
    }

}
