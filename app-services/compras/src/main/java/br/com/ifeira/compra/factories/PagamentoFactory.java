package br.com.ifeira.compra.factories;

import br.com.ifeira.compra.entity.Pagamento;
import br.com.ifeira.compra.shared.entity.Pedido;

public interface PagamentoFactory {
    public Pagamento criarPagamento(Pedido pedido);
}
