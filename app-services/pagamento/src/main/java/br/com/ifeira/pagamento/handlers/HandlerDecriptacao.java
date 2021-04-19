package br.com.ifeira.pagamento.handlers;

import br.com.ifeira.pagamento.entity.PagamentoResponse;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;

public class HandlerDecriptacao extends PagamentoOutBaseHandler {
    @Override
    public PagamentoResponse handle(PagamentoDTO pagamento) {
        System.out.println("B");
        if(this.getNext() != null) {
            return this.getNext().handle(pagamento);
        }
        return null;
    }
}
