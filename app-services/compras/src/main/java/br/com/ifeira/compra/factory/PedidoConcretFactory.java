package br.com.Ifeira.compra.factory;

import br.com.Ifeira.compra.entity.shared.Carrinho;
import br.com.Ifeira.compra.entity.shared.Pedido;

public class PedidoConcretFactory implements PedidoFactory {
    @Override
    public Pedido criarPedido(Carrinho carrinho) {
        return null;
    }
}
