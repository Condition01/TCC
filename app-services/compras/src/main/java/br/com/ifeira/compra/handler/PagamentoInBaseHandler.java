package br.com.Ifeira.compra.handlers;

import br.com.Ifeira.compra.factory.PagamentoInHandler;

public abstract class PagamentoInBaseHandler implements PagamentoInHandler {
    @Override
    public  void setNext(PagamentoInHandler pagamentoInHandler) {

    }
}
