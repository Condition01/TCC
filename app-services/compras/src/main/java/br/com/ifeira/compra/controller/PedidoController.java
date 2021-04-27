package br.com.ifeira.compra.controller;

import br.com.ifeira.compra.shared.entity.Carrinho;
import br.com.ifeira.compra.shared.entity.Cupom;
import br.com.ifeira.compra.shared.entity.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoController {

    public Pedido fecharPedido(Carrinho carrinho, Cupom cupom){
        return null;
    }

    public boolean cancelarPedido(int numeroPedido){
        return false;
    }

}
