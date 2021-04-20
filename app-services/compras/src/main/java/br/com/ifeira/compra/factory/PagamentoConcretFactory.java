package br.com.Ifeira.compra.factory;

import br.com.Ifeira.compra.entity.Pagamento;
import br.com.Ifeira.compra.entity.shared.Pedido;

public class PagamentoConcretFactory implements PagamentoFactory{
    @Override
    public Pagamento criarPagamento(Pedido pedido) {
        return null;
    }
}
