package br.com.ifeira.pagamento.controller;

import br.com.ifeira.compra.shared.utils.NotificacaoEmail;
import br.com.ifeira.compra.shared.utils.Notificavel;
import br.com.ifeira.pagamento.config.APIConfig;
import br.com.ifeira.pagamento.config.MailingConfig;
import br.com.ifeira.pagamento.dao.PagamentoDAO;
import br.com.ifeira.pagamento.entity.PagamentoResponse;
import br.com.ifeira.pagamento.entity.PagamentosRealizadosProdutor;
import br.com.ifeira.pagamento.exceptions.PagamentoInvalidoException;
import br.com.ifeira.pagamento.factories.PagamentoOutConcretHandlerFactory;
import br.com.ifeira.pagamento.factories.PagamentoOutHandlerFactory;
import br.com.ifeira.pagamento.handlers.PagamentoOutHandler;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Component
public class PagamentoController {

    @Autowired
    private APIConfig apiConfig;
    @Autowired
    private RestTemplate rTemplate;
    @Autowired
    private MailingConfig mailingConfig;
    @Autowired
    private PagamentosRealizadosProdutor pagamentoProdutor;
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

    public boolean validaDadosPag(PagamentoDTO pagamento) {
        return (pagamento.getNumeroCartao() != null
                && pagamento.getCvv() != null && pagamento.getValidadeCartao() != null && pagamento.getNomeCartao() != null);
    }

    public boolean validaCancelamento(PagamentoDTO pagamentoDTO) {
        return this.pagamentoDAO.verificarCancelamento(pagamentoDTO);
    }

    public void processarRequisicao(PagamentoDTO pagamento) {
        PagamentoOutHandler pagChain = this.pagFactory.criarPagamentoOutChain(this.apiConfig, this.rTemplate);
        try {
            PagamentoResponse pagamentoResponse = pagChain.handle(pagamento);
            mapearEstadoPagamento(pagamento, pagamentoResponse.getStatus());
            persistirPagamentosRealizados(pagamento);
            String mensagem = "Pagamento referente ao PEDIDO " + pagamento.getNumeroPedido() + " STATUS: " + pagamento.getStatus();
            notificar(pagamento, pagamento.getEmail(), mensagem);
            atualizarSaldoADM(pagamento);
        } catch (Exception e) {
            if(!(e instanceof PagamentoInvalidoException)) {
                pagamentoProdutor.enfileirarPagamentosComErro(pagamento);
                logger.error(e.getMessage());
            }
        }
    }

    public void mapearEstadoPagamento(PagamentoDTO pagamento, String status) {
        if (status.equals("CONFIRMED")) {
            pagamento.setStatus("CONFIRMADO");
        } else {
            pagamento.setStatus("CANCELADO");
        }
    }

    public void persistirPagamentosRealizados(PagamentoDTO pagamento) {
        if (pagamento.getStatus().equals("CONFIRMADO")) {
            pagamentoProdutor.enfileirarPagamentosConcluidos(pagamento);
            pagamentoDAO.persistirPagamentosComSucesso(pagamento);
        } else {
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

    }

    @PostConstruct
    public void setarFactory() {
        pagFactory = new PagamentoOutConcretHandlerFactory();
    }

}
