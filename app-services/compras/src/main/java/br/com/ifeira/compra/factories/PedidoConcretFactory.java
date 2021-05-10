package br.com.ifeira.compra.factories;

import br.com.ifeira.compra.shared.entity.Carrinho;
import br.com.ifeira.compra.shared.entity.Cupom;
import br.com.ifeira.compra.shared.entity.Pedido;
import br.com.ifeira.compra.shared.entity.Pessoa;

import java.util.Date;

public class PedidoConcretFactory implements PedidoFactory {
    @Override
    public Pedido criarPedido(Carrinho carrinho, Cupom cupom, Pessoa pessoa) {
        Pedido pedido = new Pedido();
        pedido.setCarrinho(carrinho);
        pedido.setCupom(cupom);
        pedido.setCliente(pessoa);
        pedido.setData(new Date());
        pedido.marcarPendente();
        pedido.calcularValorTotal();
        return pedido;
    }
}
