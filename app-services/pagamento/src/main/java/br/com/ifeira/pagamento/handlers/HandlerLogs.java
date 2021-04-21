package br.com.ifeira.pagamento.handlers;

import br.com.ifeira.pagamento.entity.PagamentoResponse;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HandlerLogs  extends PagamentoOutBaseHandler {

    private Logger logger = LoggerFactory.getLogger(HandlerLogs.class);

    @Override
    public PagamentoResponse handle(PagamentoDTO pagamento) {
        logger.info(pagamento.toString());
        if(this.getNext() != null) {
            return this.getNext().handle(pagamento);
        }
        return null;
    }
}
