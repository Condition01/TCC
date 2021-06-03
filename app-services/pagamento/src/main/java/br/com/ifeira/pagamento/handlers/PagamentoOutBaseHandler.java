package br.com.ifeira.pagamento.handlers;

import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;

public abstract class PagamentoOutBaseHandler implements PagamentoOutHandler {

    private PagamentoOutHandler next;

    @Override
    public abstract PagamentoDTO handle(PagamentoDTO pagamento) throws Exception;

    @Override
    public void setarProximo(PagamentoOutHandler handler) {
        this.next = handler;
    }

    public PagamentoOutHandler getNext() {
        return next;
    }

}
