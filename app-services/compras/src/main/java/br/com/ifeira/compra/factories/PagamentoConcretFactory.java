package br.com.ifeira.compra.factories;

import br.com.ifeira.compra.entity.Pagamento;
import br.com.ifeira.compra.shared.entity.Pedido;

public class PagamentoConcretFactory implements PagamentoFactory {
    @Override
    public Pagamento criarPagamento(Pedido pedido) {
        return null;
    }
}
