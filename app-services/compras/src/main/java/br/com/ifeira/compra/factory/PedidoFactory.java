package br.com.Ifeira.compra.factory;

import br.com.Ifeira.compra.entity.shared.Carrinho;
import br.com.Ifeira.compra.entity.shared.Pedido;

public interface PedidoFactory {
    public Pedido criarPedido(Carrinho carrinho);

}
