package br.com.Ifeira.compra.factory;

import br.com.Ifeira.compra.entity.Pagamento;
import br.com.Ifeira.compra.entity.shared.Pedido;

public interface PagamentoFactory {
    public Pagamento criarPagamento(Pedido pedido);
}
