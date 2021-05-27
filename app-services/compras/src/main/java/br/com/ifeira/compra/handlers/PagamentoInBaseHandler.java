package br.com.ifeira.compra.handlers;

import br.com.ifeira.compra.entity.Pagamento;

public abstract class PagamentoInBaseHandler implements PagamentoInHandler {

    private PagamentoInHandler next;

    @Override
    public abstract Pagamento handle(Pagamento pagamento) throws Exception;

    @Override
    public void setNext(PagamentoInHandler handler) {
        this.next = handler;
    }

    public PagamentoInHandler getNext() {
        return next;
    }

}
