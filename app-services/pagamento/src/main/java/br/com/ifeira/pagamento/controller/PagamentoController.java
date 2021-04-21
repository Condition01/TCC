package br.com.ifeira.pagamento.controller;

import br.com.ifeira.pagamento.entity.PagamentoResponse;
import br.com.ifeira.pagamento.factories.PagamentoOutConcretHandlerFactory;
import br.com.ifeira.pagamento.factories.PagamentoOutHandlerFactory;
import br.com.ifeira.pagamento.handlers.PagamentoOutHandler;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PagamentoController {

    private Logger logger = LoggerFactory.getLogger(PagamentoController.class);

    private PagamentoOutHandlerFactory pagFactory;

    public void processarRequisicao(PagamentoDTO pagamento) {
        PagamentoOutHandler pagChain = this.pagFactory.criarPagamentoOutChain();
        try {
            PagamentoResponse pagamentoResponse = pagChain.handle(pagamento);
        }catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void persistirPagamentosRealizados(PagamentoDTO pagamentoDTO) {
        
    }

    public void notificar() {

    }

    public void atualizarSaldoADM(PagamentoDTO pagamentoDTO) {

    }

    @PostConstruct
    public void setarFactory() {
        pagFactory = new PagamentoOutConcretHandlerFactory();
    }

}
