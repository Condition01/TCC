package br.com.ifeira.pagamento.controller;

import br.com.ifeira.compra.shared.utils.NotificacaoEmail;
import br.com.ifeira.compra.shared.utils.Notificavel;
import br.com.ifeira.pagamento.config.APIConfig;
import br.com.ifeira.pagamento.config.MailingConfig;
import br.com.ifeira.pagamento.dao.PagamentoDAO;
import br.com.ifeira.pagamento.entity.PagamentoResponse;
import br.com.ifeira.pagamento.entity.PagamentosProdutor;
import br.com.ifeira.pagamento.exceptions.PagamentoInvalidoException;
import br.com.ifeira.pagamento.factories.PagamentoOutConcretHandlerFactory;
import br.com.ifeira.pagamento.factories.PagamentoOutHandlerFactory;
import br.com.ifeira.pagamento.handlers.PagamentoOutHandler;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Date;
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
        PagamentoOutHandler pagChain = this.pagFactory.criarPagamentoOutChain(this.apiConfig, this.rTemplate, this.jdbcTemplate);
        try {
            checarTentativas(pagamento);
            PagamentoResponse pagamentoResponse = pagChain.handle(pagamento);
            mapearEstadoPagamento(pagamento, pagamentoResponse.getStatus());
            persistirPagamentosRealizados(pagamento);
            String mensagem = "Pagamento referente ao PEDIDO " + pagamento.getNumeroPedido() + " STATUS: " + pagamento.getStatus();
            notificar(pagamento, pagamento.getEmail(), mensagem);
            atualizarSaldoADM(pagamento);
        } catch (Exception e) {
            if(!(e instanceof PagamentoInvalidoException)) {
                pagamento.setTentativasMQ(pagamento.getTentativasMQ()+1);
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

}
