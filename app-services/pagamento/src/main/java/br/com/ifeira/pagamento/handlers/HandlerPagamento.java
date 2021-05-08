package br.com.ifeira.pagamento.handlers;

import br.com.ifeira.pagamento.config.APIConfig;
import br.com.ifeira.pagamento.entity.PagamentoResponse;
import br.com.ifeira.pagamento.entity.juno.JunoAddress;
import br.com.ifeira.pagamento.entity.juno.JunoBilling;
import br.com.ifeira.pagamento.entity.juno.JunoCreditCardDetails;
import br.com.ifeira.pagamento.entity.juno.JunoPaymentReq;
import br.com.ifeira.pagamento.exceptions.PagamentoInvalidoException;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class HandlerPagamento extends PagamentoOutBaseHandler {

    private APIConfig apiConfig;
    private RestTemplate restTemplate;

    public HandlerPagamento(APIConfig apiConfig, RestTemplate restTemplate) {
        this.apiConfig = apiConfig;
        this.restTemplate = restTemplate;
    }

    @Override
    public PagamentoResponse handle(PagamentoDTO pagamento) throws Exception  {
        String token = pegarTokenAutorizacaoAPIExterna();
        PagamentoResponse pagamentoResponse = enviarPagamento(pagamento, token);
        return pagamentoResponse;
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

    private PagamentoResponse enviarPagamento(PagamentoDTO pagamento, String token) throws IOException, PagamentoInvalidoException {
        this.restTemplate.setErrorHandler(new ReqErrorHandler());

        JunoPaymentReq junoPaymentReq = parseDTOtoJuno(pagamento);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("X-Api-Version", this.apiConfig.API_VERSION);
        headers.add("X-Resource-Token", this.apiConfig.PRIVATE_TOKEN);
        headers.add("Authorization", "Bearer " + token);

        HttpEntity<JunoPaymentReq> entity = new HttpEntity<>(junoPaymentReq, headers);

        ResponseEntity<String> response = this.restTemplate.postForEntity(this.apiConfig.API_URL, entity, String.class);

        if(response.getStatusCode() == HttpStatus.BAD_REQUEST) {
            throw new PagamentoInvalidoException(pagamento);
        }

        JsonNode jsonObjResp = new ObjectMapper().readTree(response.getBody());
        String txId = jsonObjResp.get("transactionId").asText();
        String statusPagamento = jsonObjResp.get("payments").get(0).get("status").asText();

        PagamentoResponse pagamentoResponse = PagamentoResponse.dtoToResponse(pagamento, statusPagamento, txId);

        return pagamentoResponse;
    }

    private JunoPaymentReq parseDTOtoJuno(PagamentoDTO pagamento) {
        JunoCreditCardDetails jCredDetails = new JunoCreditCardDetails();
        jCredDetails.setCredCardId(pagamento.getCredId());

        JunoAddress jAdresss = new JunoAddress();
        jAdresss.setStreet(pagamento.getRua());
        jAdresss.setNumber(pagamento.getNumeroCasa());
        jAdresss.setCity(pagamento.getCidade());
        jAdresss.setComplement(pagamento.getComplemento());
        jAdresss.setNeighborhood(pagamento.getBairro());
        jAdresss.setState(pagamento.getUF());
        jAdresss.setPostCode(pagamento.getCodigoPostal());

        JunoBilling jBilling = new JunoBilling();
        jBilling.setEmail(pagamento.getEmail());
        jBilling.setAddress(jAdresss);

        JunoPaymentReq paymentReq = new JunoPaymentReq();
        paymentReq.setChargeId(pagamento.getIdCobranca());
        paymentReq.setCreditCardDetails(jCredDetails);
        paymentReq.setBilling(jBilling);

        return paymentReq;
    }

    public void setApiConfig(APIConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

}
