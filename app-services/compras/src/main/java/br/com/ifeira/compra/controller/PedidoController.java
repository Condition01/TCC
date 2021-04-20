package br.com.Ifeira.compra.controller;

import br.com.Ifeira.compra.entity.shared.Carrinho;
import br.com.Ifeira.compra.entity.shared.Cupom;
import br.com.Ifeira.compra.entity.shared.Pedido;

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
