package br.com.ifeira.compra.controller;

import br.com.ifeira.compra.shared.entity.Carrinho;
import br.com.ifeira.compra.shared.entity.Cupom;
import br.com.ifeira.compra.shared.entity.Pedido;

public class PedidoController {
    PedidoController pedidoController;

    public Pedido fecharPedido(Carrinho carrinho, Cupom cupom){
        return null;
    }

    public PedidoController getPedidoController() {
        return pedidoController;
    }

    public boolean cancelarPedido(int numeroPedido){
        return false;
    }
}
