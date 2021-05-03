package br.com.ifeira.compra.handlers;

import br.com.ifeira.compra.entity.Pagamento;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HandlerLogs extends PagamentoInBaseHandler {

    private Logger logger = LoggerFactory.getLogger(HandlerLogs.class);

    @Override
    public PagamentoDTO handle(Pagamento pagamento) throws Exception {
        logger.info(pagamento.toString());
        if(this.getNext() != null) {
            return this.getNext().handle(pagamento);
        }
        return null;
    }

}
