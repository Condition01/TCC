package br.com.ifeira.compra.factories;

import br.com.ifeira.compra.shared.entity.Carrinho;
import br.com.ifeira.compra.shared.entity.Pedido;

public interface PedidoFactory {
    public Pedido criarPedido(Carrinho carrinho);
}
