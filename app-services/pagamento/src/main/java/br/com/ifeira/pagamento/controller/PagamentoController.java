package br.com.ifeira.pagamento.controller;

import br.com.ifeira.compra.shared.utils.NotificacaoEmail;
import br.com.ifeira.compra.shared.utils.Notificavel;
import br.com.ifeira.pagamento.config.APIConfig;
import br.com.ifeira.pagamento.config.MailingConfig;
import br.com.ifeira.pagamento.dao.PagamentoDAO;
import br.com.ifeira.pagamento.entity.PagamentoResponse;
import br.com.ifeira.pagamento.entity.PagamentosProdutor;
import br.com.ifeira.pagamento.entity.juno.JunoAddress;
import br.com.ifeira.pagamento.entity.juno.JunoBilling;
import br.com.ifeira.pagamento.entity.juno.JunoCreditCardDetails;
import br.com.ifeira.pagamento.entity.juno.JunoPaymentReq;
import br.com.ifeira.pagamento.exceptions.PagamentoInvalidoException;
import br.com.ifeira.pagamento.factories.PagamentoOutConcretHandlerFactory;
import br.com.ifeira.pagamento.factories.PagamentoOutHandlerFactory;
import br.com.ifeira.pagamento.handlers.PagamentoOutHandler;
import br.com.ifeira.pagamento.handlers.ReqErrorHandler;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;

@Component
public class PagamentoController {

    @Autowired
    private APIConfig apiConfig;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MailingConfig mailingConfig;
    @Autowired
    private PagamentosProdutor pagamentoProdutor;
    private JdbcTemplate jdbcTemplate;
    private PagamentoDAO pagamentoDAO;
    private Notificavel notificador;

    private Logger logger = LoggerFactory.getLogger(PagamentoController.class);

    private PagamentoOutHandlerFactory pagFactory;

    public PagamentoController(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.pagFactory = new PagamentoOutConcretHandlerFactory();
        this.pagamentoDAO = new PagamentoDAO(jdbcTemplate);
    }

    public void processarRequisicao(PagamentoDTO pagamento) throws Exception {
        PagamentoOutHandler pagChain = this.pagFactory.criarPagamentoOutChain(this.jdbcTemplate);
        try {
            checarTentativas(pagamento);
            PagamentoDTO pagamentoTratado = pagChain.handle(pagamento);
            PagamentoResponse pagamentoResponse = pagar(pagamentoTratado);
            mapearEstadoPagamento(pagamento, pagamentoResponse.getStatus());
            persistirPagamentosRealizados(pagamento);
            String mensagem = "Pagamento referente ao PEDIDO " + pagamento.getNumeroPedido() + " STATUS: " + pagamento.getStatus();
            notificar(pagamento, pagamento.getEmail(), mensagem);
            atualizarSaldoADM(pagamento);
        } catch (Exception e) {
            if (!(e instanceof PagamentoInvalidoException)) {
                pagamento.setTentativasMQ(pagamento.getTentativasMQ() + 1);
                logger.info("Pagamento: " + pagamento.getIdPagamento() + " processado com falha - RE-INSERINDO");
                this.pagamentoProdutor.enfileirarPagamentosComErro(pagamento);
                this.logger.error(e.getMessage());
            } else {
                logger.info("Pagamento: " + pagamento.getIdPagamento() + " CANCELADO");
                this.pagamentoDAO.persistirPagamentosComErro(pagamento);
                pagamento.setStatus("CANCELADO");
                String mensagem = "Pagamento referente ao PEDIDO " + pagamento.getNumeroPedido() + " STATUS: " + pagamento.getStatus();
                this.notificar(pagamento, pagamento.getEmail(), mensagem);
                throw new AmqpRejectAndDontRequeueException("DONT");
            }
        }
    }

    public void checarTentativas(PagamentoDTO pagamento) throws PagamentoInvalidoException {
        if (pagamento.getTentativasMQ() >= 3) {
            throw new PagamentoInvalidoException(pagamento);
        }
    }

    public void mapearEstadoPagamento(PagamentoDTO pagamento, String status) {
        if (status.equals("CONFIRMED")) {
            pagamento.setData(new Date());
            pagamento.setStatus("CONFIRMADO");
        } else {
            pagamento.setStatus("CANCELADO");
        }
    }

    @Transactional
    public void persistirPagamentosRealizados(PagamentoDTO pagamento) throws Exception {
        if (pagamento.getStatus().equals("CONFIRMADO")) {
            logger.info("Pagamento: " + pagamento.getIdPagamento() + " processado com sucesso");
            pagamentoDAO.persistirPagamentosComSucesso(pagamento);
            pagamentoProdutor.enfileirarPagamentosConcluidos(pagamento);
        } else {
            logger.info("Pagamento: " + pagamento.getIdPagamento() + " CANCELADO");
            pagamentoDAO.persistirPagamentosComErro(pagamento);
        }
    }

    public void notificar(PagamentoDTO pagamento, String email, String mensagem) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", this.mailingConfig.AUTH);
        properties.put("mail.smtp.starttls.enable", this.mailingConfig.STARTTLS);
        properties.put("mail.smtp.host", this.mailingConfig.HOST);
        properties.put("mail.smtp.port", this.mailingConfig.PORT);

        notificador = new NotificacaoEmail(properties, this.mailingConfig.ACCOUNT, this.mailingConfig.PASSWORD);

        this.notificador.enviarNotificacao(mensagem, email, "Status Pedido " + pagamento.getNumeroPedido());
    }

    public void atualizarSaldoADM(PagamentoDTO pagamento) {
        this.jdbcTemplate.update(
                "INSERT INTO SALDO_ADMIN\n" +
                        "(ID_PAGAMENTO, VALOR_TOTAL)\n" +
                        "VALUES(?, ?);\n", pagamento.getIdPagamento(), pagamento.getValorTotalPedido());
    }

    @PostConstruct
    public void setarFactory() {
        pagFactory = new PagamentoOutConcretHandlerFactory();
    }

    public PagamentoResponse pagar(PagamentoDTO pagamento) throws Exception {
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

        if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
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

}
