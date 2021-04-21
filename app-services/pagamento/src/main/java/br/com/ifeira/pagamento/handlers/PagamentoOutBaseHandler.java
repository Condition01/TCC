package br.com.ifeira.pagamento.handlers;

import br.com.ifeira.pagamento.entity.PagamentoResponse;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;

public abstract class PagamentoOutBaseHandler implements PagamentoOutHandler {
    private PagamentoOutHandler next;

    @Override
    public abstract PagamentoResponse handle(PagamentoDTO pagamento);

    @Override
    public void setarProximo(PagamentoOutHandler handler) {
        this.next = handler;
    }

    public PagamentoOutHandler getNext() {
        return next;
    }
}
