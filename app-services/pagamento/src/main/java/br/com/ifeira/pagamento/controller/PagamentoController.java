package br.com.ifeira.pagamento.controller;

import br.com.ifeira.compra.shared.utils.NotificacaoEmail;
import br.com.ifeira.compra.shared.utils.Notificavel;
import br.com.ifeira.pagamento.config.APIConfig;
import br.com.ifeira.pagamento.config.MailingConfig;
import br.com.ifeira.pagamento.entity.PagamentoResponse;
import br.com.ifeira.pagamento.factories.PagamentoOutConcretHandlerFactory;
import br.com.ifeira.pagamento.factories.PagamentoOutHandlerFactory;
import br.com.ifeira.pagamento.handlers.PagamentoOutHandler;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private Logger logger = LoggerFactory.getLogger(PagamentoController.class);

    private PagamentoOutHandlerFactory pagFactory;

    PagamentoController() {
        this.pagFactory = new PagamentoOutConcretHandlerFactory();
    }

    public void processarRequisicao(PagamentoDTO pagamento) {
        PagamentoOutHandler pagChain = this.pagFactory.criarPagamentoOutChain(this.apiConfig, this.rTemplate);
        try {
            PagamentoResponse pagamentoResponse = pagChain.handle(pagamento);
            persistirPagamentosRealizados(pagamento);
            notificar(pagamentoResponse);
        }catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void persistirPagamentosRealizados(PagamentoDTO pagamentoDTO) {
        
    }

    public void notificar(PagamentoResponse response) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", this.mailingConfig.AUTH);
        properties.put("mail.smtp.starttls.enable", this.mailingConfig.STARTTLS);
        properties.put("mail.smtp.host", this.mailingConfig.HOST);
        properties.put("mail.smtp.port", this.mailingConfig.PORT);

        Notificavel notificador = new NotificacaoEmail(properties, this.mailingConfig.ACCOUNT, this.mailingConfig.PASSWORD);

        String mensagem = "Seu pagamento foi do pedido " + response.getNumeroPedido() + " foi " + response.getStatus();

        notificador.enviarNotificacao("Teste de integração com SERVIDOR SMTP para envio de notificações por email", "brunofc11@gmail.com ", "TESTE DO IFEIRA EMAIL");
    }

    public void atualizarSaldoADM(PagamentoDTO pagamentoDTO) {

    }

    @PostConstruct
    public void setarFactory() {
        pagFactory = new PagamentoOutConcretHandlerFactory();
    }

}
