package br.com.ifeira.compra.controller;

import br.com.ifeira.compra.config.APIConfig;
import br.com.ifeira.compra.config.MailingConfig;
import br.com.ifeira.compra.dao.PagamentoDAO;
import br.com.ifeira.compra.entity.Pagamento;
import br.com.ifeira.compra.entity.PagamentoProdutor;
import br.com.ifeira.compra.factories.PagamentoInConcretHandlerFactory;
import br.com.ifeira.compra.factories.PagamentoInHandlerFactory;
import br.com.ifeira.compra.handlers.PagamentoInHandler;
import br.com.ifeira.compra.shared.dao.*;
import br.com.ifeira.compra.shared.entity.Carrinho;
import br.com.ifeira.compra.shared.entity.Pedido;
import br.com.ifeira.compra.shared.entity.Pessoa;
import br.com.ifeira.compra.shared.enums.StatusPedido;
import br.com.ifeira.compra.shared.utils.NotificacaoEmail;
import br.com.ifeira.compra.shared.utils.Notificavel;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.*;

@Component
public class PagamentoController {

    @Autowired
    private APIConfig apiConfig;
    private RestTemplate restTemplate;
    @Autowired
    private MailingConfig mailingConfig;
    @Autowired
    private PagamentoProdutor pagamentoProdutor;
    private Persistivel<Pagamento, Long> pagamentoDAO;
    private Persistivel<Pedido, Long> pedidoDAO;
    private PersistivelContextual<Carrinho, Long> carrinhoDAO;
    private Persistivel<Pessoa, String> pessoaDAO;
    private JdbcTemplate jdbcTemplate;
    private Notificavel notificador;
    private PagamentoInHandlerFactory pagFactory;
    private Logger logger = LoggerFactory.getLogger(PagamentoController.class);

    public PagamentoController(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.pagamentoDAO = new PagamentoDAO(jdbcTemplate);
        this.pedidoDAO = new PedidoDAO(jdbcTemplate);
        this.pessoaDAO = new PessoaDAO(jdbcTemplate);
        this.carrinhoDAO = new CarrinhoDAO(jdbcTemplate);
        this.pagFactory = new PagamentoInConcretHandlerFactory();
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    public PagamentoDTO enfileirarPagamento(Pagamento pagamento) throws Exception {
        if(pagamento.getPedido() != null && !pagamento.getPedido().getStatusPedido().equals(StatusPedido.PENDENTE)) throw new Exception("Não é possivel inserir PEDIDO que não esta PENDENTE");
        PagamentoInHandler pagChain = this.pagFactory.criarPagamentoInChain(jdbcTemplate);
        pagamento.setData(new Date());
        pagamento.marcarPendente();
        setarDiaEntrega(pagamento);
        if (pagamento.getCreditCardId() == null) {
            String token = pegarTokenAutorizacaoAPIExterna();
            pagamento.setCreditCardId(tokenizarCartao(pagamento.getCreditCardHash(), token));
        }
        Pagamento returnedPag = pagChain.handle(pagamento);
        PagamentoDTO pagDTO = persistirEnfileirar(returnedPag);
        notificar(pagDTO);
        return pagDTO;
    }


    @Transactional
    public PagamentoDTO persistirEnfileirar(Pagamento pagamento) {
        String contextoFeira = pagamento.getPedido()
                .getCarrinho()
                .getListaProdutoQuantidade()
                .get(0)
                .getProdutoFeira()
                .getFeira()
                .getContext();

        Carrinho carrinho = pagamento.getPedido().getCarrinho();

        Pedido pedidoRetornado = this.pedidoDAO.salvar(pagamento.getPedido());

        pagamento.setPedido(pedidoRetornado);
        pagamento = this.pagamentoDAO.salvar(pagamento);

        carrinho.setPedidoRef(pedidoRetornado.getNumeroPedido());

        this.carrinhoDAO.salvar(carrinho, contextoFeira);

        return this.pagamentoProdutor.enfileiraPagamento(pagamento);
    }

    private String tokenizarCartao(String creditCardHash, String token) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("X-Api-Version", this.apiConfig.API_VERSION);
        headers.add("X-Resource-Token", this.apiConfig.PRIVATE_TOKEN);
        headers.add("Authorization", "Bearer " + token);

        Map<String, String> cardHash = new HashMap();
        cardHash.put("creditCardHash", creditCardHash);

        HttpEntity<Map<String, String>> entity = new HttpEntity<Map<String, String>>(cardHash, headers);

        ResponseEntity<String> response =
                this.restTemplate.exchange(apiConfig.URL_TOKENIZACAO,
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

    private void setarDiaEntrega(Pagamento pagamento) {
        Integer dia = pagamento
                .getPedido()
                .getCarrinho()
                .getListaProdutoQuantidade()
                .get(0)
                .getProdutoFeira()
                .getFeira()
                .getDiaEntrega();

        Date now = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        Integer hoje = calendar.get(Calendar.DAY_OF_WEEK);

        Integer diasAdicionados = hoje.equals(dia) ? 7 : dia - hoje;

        calendar.add(Calendar.DATE, diasAdicionados);

        pagamento.getPedido().setDataEntrega(calendar.getTime());
    }

    public List<Pagamento> listarPagamentos(Principal principal) {
        Pessoa pessoa = this.pessoaDAO.buscar(principal.getName());
        Object[] parametros = new Object[5];
        parametros[0] = pessoa;
        return this.pagamentoDAO.buscarComParametros(parametros);
    }

    public void notificar(PagamentoDTO pagamento) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", this.mailingConfig.AUTH);
        properties.put("mail.smtp.starttls.enable", this.mailingConfig.STARTTLS);
        properties.put("mail.smtp.host", this.mailingConfig.HOST);
        properties.put("mail.smtp.port", this.mailingConfig.PORT);

        notificador = new NotificacaoEmail(properties, this.mailingConfig.ACCOUNT, this.mailingConfig.PASSWORD);

        String mensagem = "Pagamento referente ao PEDIDO " + pagamento.getNumeroPedido() + " STATUS: " + pagamento.getStatus();
        this.notificador.enviarNotificacao(mensagem, pagamento.getEmail(), "Status Pedido " + pagamento.getNumeroPedido());
    }

}
