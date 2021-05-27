package br.com.ifeira.compra.handlers;

import br.com.ifeira.compra.entity.Pagamento;

public interface PagamentoInHandler {
    void setNext(PagamentoInHandler pagamentoInHandler);
    Pagamento handle(Pagamento pagamento) throws Exception;
}
