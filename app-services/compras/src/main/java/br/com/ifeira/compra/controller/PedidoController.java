package br.com.ifeira.compra.controller;

import br.com.ifeira.compra.config.APIConfig;
import br.com.ifeira.compra.entity.juno.JunoAddress;
import br.com.ifeira.compra.entity.juno.JunoBilling;
import br.com.ifeira.compra.entity.juno.JunoCharge;
import br.com.ifeira.compra.entity.juno.JunoChargeReq;
import br.com.ifeira.compra.factories.PedidoConcretFactory;
import br.com.ifeira.compra.factories.PedidoFactory;
import br.com.ifeira.compra.shared.dao.CupomDAO;
import br.com.ifeira.compra.shared.dao.PedidoDAO;
import br.com.ifeira.compra.shared.dao.Persistivel;
import br.com.ifeira.compra.shared.dao.PessoaDAO;
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
import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class PedidoController {

    @Autowired
    private APIConfig apiConfig;
    private RestTemplate restTemplate;
    private Persistivel<Pedido, Long> pedidoDAO;
    private Persistivel<Pessoa, String> pessoaDAO;
    private Persistivel<Cupom, String> cupomDAO;
    private Logger logger = LoggerFactory.getLogger(PedidoController.class);
    private PedidoFactory pedidoFactory;

    public PedidoController(@Autowired JdbcTemplate jdbcTemplate) throws Exception {
        this.pedidoDAO = new PedidoDAO(jdbcTemplate);
        this.pessoaDAO = new PessoaDAO(jdbcTemplate);
        this.cupomDAO = new CupomDAO(jdbcTemplate);
        this.pedidoFactory = new PedidoConcretFactory();
        this.restTemplate = new RestTemplate();
    }

    public Pedido fecharPedido(Carrinho carrinho, String cupom, Principal principal) throws Exception {
        Pessoa pessoa = this.pessoaDAO.buscar(principal.getName());
        Cupom cupomObj = this.cupomDAO.buscar(cupom);
        Pedido pedido = pedidoFactory.criarPedido(carrinho, cupomObj, pessoa);
        pedido.setCobranca(gerarCobranca(pedido, "CREDIT_CARD"));
        return pedido;
    }

    private String gerarCobranca(Pedido pedido, String tpPagamento) throws Exception {
        String token = pegarTokenAutorizacaoAPIExterna();
        return enviarReqCobranca(pedido, tpPagamento, token);
    }

    public boolean cancelarPedido(int numeroPedido){
        return false;
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

    private String enviarReqCobranca(Pedido pedido, String tipoPagamento, String token) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("X-Api-Version", this.apiConfig.API_VERSION);
        headers.add("X-Resource-Token", this.apiConfig.PRIVATE_TOKEN);
        headers.add("Authorization", "Bearer " + token);

        JunoCharge junoCharge = new JunoCharge();
        junoCharge.setAmount(pedido.getValorTotal());
        junoCharge.setDescription("Cobrança sobre pedido");
        List<String> paymentTypes = new ArrayList<>();
        paymentTypes.add(tipoPagamento);
        junoCharge.setPaymentTypes(paymentTypes);

        JunoAddress junoAddress = new JunoAddress();
        junoAddress.setNeighborhood(pedido.getCliente().getEndereco().getBairro());
        junoAddress.setCity(pedido.getCliente().getEndereco().getCidade());
        junoAddress.setComplement(pedido.getCliente().getEndereco().getComplemento());
        junoAddress.setNumber(pedido.getCliente().getEndereco().getNumero());
        junoAddress.setPostCode(pedido.getCliente().getEndereco().getCep());
        junoAddress.setState(pedido.getCliente().getEndereco().getUF());
        junoAddress.setStreet(pedido.getCliente().getEndereco().getLogradouro());

        JunoBilling junoBilling = new JunoBilling();
        junoBilling.setEmail(pedido.getCliente().getEmail());
        junoBilling.setName(pedido.getCliente().getNome());
        junoBilling.setDocument(pedido.getCliente().getCpf());
        junoBilling.setAddress(junoAddress);

        JunoChargeReq junoChargeReq = new JunoChargeReq();
        junoChargeReq.setCharge(junoCharge);
        junoChargeReq.setBilling(junoBilling);

        HttpEntity<JunoChargeReq> entity = new HttpEntity<>(junoChargeReq, headers);

        ResponseEntity<String> response = this.restTemplate.postForEntity(this.apiConfig.API_URL, entity, String.class);

        JsonNode jsonObjResp = new ObjectMapper().readTree(response.getBody());
        return jsonObjResp.get("_embedded").get("charges").get(0).get("id").asText();
    }

    public List<Pedido> listarPedidos(Principal principal) {
        Pessoa pessoa = this.pessoaDAO.buscar(principal.getName());
        Object[] parametros = new Object[5];
        parametros[0] = pessoa;
        return this.pedidoDAO.buscarComParametros(parametros);
    }

}
