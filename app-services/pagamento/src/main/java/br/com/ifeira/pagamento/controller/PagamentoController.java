package br.com.ifeira.pagamento.controller;

import br.com.ifeira.compra.shared.dao.Persistivel;
import br.com.ifeira.compra.shared.utils.NotificacaoEmail;
import br.com.ifeira.compra.shared.utils.Notificavel;
import br.com.ifeira.pagamento.config.APIConfig;
import br.com.ifeira.pagamento.config.MailingConfig;
import br.com.ifeira.pagamento.dao.PagamentoDAO;
import br.com.ifeira.pagamento.entity.PagamentoResponse;
import br.com.ifeira.pagamento.entity.PagamentosRealizadosProdutor;
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
    private Persistivel<PagamentoDTO, Integer> pagamentoDAO;
    private Notificavel notificador;

    private Logger logger = LoggerFactory.getLogger(PagamentoController.class);

    private PagamentoOutHandlerFactory pagFactory;

    public PagamentoController(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.pagFactory = new PagamentoOutConcretHandlerFactory();
        this.pagamentoDAO = new PagamentoDAO(jdbcTemplate);
    }

    public void processarRequisicao(PagamentoDTO pagamento) {
        PagamentoOutHandler pagChain = this.pagFactory.criarPagamentoOutChain(this.apiConfig, this.rTemplate);
        try {
            PagamentoResponse pagamentoResponse = pagChain.handle(pagamento);
            mapearEstadoPagamento(pagamento, pagamentoResponse.getStatus());
            persistirPagamentosRealizados(pagamento);
            notificar(pagamento, pagamento.getEmail());
            atualizarSaldoADM(pagamento);
        }catch (Exception e) {
            pagamentoProdutor.enfileirarPagamentosComErro(pagamento);
            logger.error(e.getMessage());
        }
    }

    public void mapearEstadoPagamento(PagamentoDTO pagamento, String status) {
        if(status.equals("CONFIRMED")) {
            pagamento.setStatus("CONFIRMADO");
        }else {
            pagamento.setStatus("CANCELADO");
        }
    }

    public void persistirPagamentosRealizados(PagamentoDTO pagamento) {
        if(pagamento.getStatus().equals("CONFIRMADO")) {
            pagamentoProdutor.enfileirarPagamentosConcluidos(pagamento);
        }
        pagamentoDAO.salvar(pagamento);
    }

    public void notificar(PagamentoDTO pagamento, String email) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", this.mailingConfig.AUTH);
        properties.put("mail.smtp.starttls.enable", this.mailingConfig.STARTTLS);
        properties.put("mail.smtp.host", this.mailingConfig.HOST);
        properties.put("mail.smtp.port", this.mailingConfig.PORT);

        notificador = new NotificacaoEmail(properties, this.mailingConfig.ACCOUNT, this.mailingConfig.PASSWORD);

        String mensagem = "Pagamento referente ao PEDIDO " + pagamento.getNumeroPedido() + " STATUS: " + pagamento.getStatus();
        this.notificador.enviarNotificacao(mensagem, email,"Status Pedido " + pagamento.getNumeroPedido());
    }

    public void atualizarSaldoADM(PagamentoDTO pagamento) {

    }

    @PostConstruct
    public void setarFactory() {
        pagFactory = new PagamentoOutConcretHandlerFactory();
    }

}
