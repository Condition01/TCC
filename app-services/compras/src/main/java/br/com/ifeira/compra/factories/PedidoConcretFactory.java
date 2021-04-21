package br.com.ifeira.compra.factories;

import br.com.ifeira.compra.shared.entity.Carrinho;
import br.com.ifeira.compra.shared.entity.Pedido;

public class PedidoConcretFactory implements PedidoFactory {
    @Override
    public Pedido criarPedido(Carrinho carrinho) {
        return null;
    }
}
