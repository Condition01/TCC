package br.com.ifeira.pagamento.handlers;

import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HandlerLogs  extends PagamentoOutBaseHandler {

    private Logger logger = LoggerFactory.getLogger(HandlerLogs.class);

    @Override
    public PagamentoDTO handle(PagamentoDTO pagamento) throws Exception  {
        logger.info("HANDLER-LOGS -> Recebendo requisição de pagamento para ser processado...");
        logger.info("HANDLER-LOGS -> Pessoa vinculada: " + pagamento.getEmail());
        logger.info("HANDLER-LOGS -> Id pagamento: " + pagamento.getIdPagamento());
        logger.info("HANDLER-LOGS -> Numero pedido vinculado: " + pagamento.getNumeroPedido());
        logger.info("HANDLER-LOGS -> Objeto: " + pagamento.toString());
        if(this.getNext() != null) {
            return this.getNext().handle(pagamento);
        }
        return pagamento;
    }
}
