package br.com.ifeira.compra.handlers;

import br.com.ifeira.compra.entity.Pagamento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HandlerLogs extends PagamentoInBaseHandler {

    private Logger logger = LoggerFactory.getLogger(HandlerLogs.class);

    @Override
    public Pagamento handle(Pagamento pagamento) throws Exception {
        logger.info("HANDLER-LOGS -> Recebendo pagamento para ser processado...");
        logger.info("HANDLER-LOGS -> Pessoa vinculada: " + pagamento.getPedido()
                .getCliente().getEmail());
        logger.info("HANDLER-LOGS -> Objeto: " + pagamento.toString());
        if(this.getNext() != null) {
            return this.getNext().handle(pagamento);
        }
        return pagamento;
    }

}
