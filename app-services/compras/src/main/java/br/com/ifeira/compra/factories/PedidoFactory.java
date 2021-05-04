package br.com.ifeira.compra.factories;

import br.com.ifeira.compra.shared.entity.Carrinho;
import br.com.ifeira.compra.shared.entity.Cupom;
import br.com.ifeira.compra.shared.entity.Pedido;
import br.com.ifeira.compra.shared.entity.Pessoa;

public interface PedidoFactory {
    public Pedido criarPedido(Carrinho carrinho, Cupom cupom, Pessoa pessoa);
}
