package br.com.ifeira.pagamento.factories;

import br.com.ifeira.compra.shared.entity.Pedido;
import br.com.ifeira.pagamento.handlers.PagamentoOutHandler;

public interface PagamentoOutHandlerFactory {
    PagamentoOutHandler criarPagamentoOutChain();
}
