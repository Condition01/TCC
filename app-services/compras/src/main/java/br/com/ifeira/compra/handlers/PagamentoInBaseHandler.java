package br.com.ifeira.compra.handlers;

import br.com.ifeira.compra.factories.PagamentoInHandler;

public abstract class PagamentoInBaseHandler implements PagamentoInHandler {
    @Override
    public  void setNext(PagamentoInHandler pagamentoInHandler) {

    }
}
