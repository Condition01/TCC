package br.com.ifeira.entrega.factories;

import br.com.ifeira.compra.shared.entity.Pedido;
import br.com.ifeira.entrega.entity.Entrega;
import br.com.ifeira.entrega.entity.Entregador;

public class EntregaConcretFactory implements EntregaFactory {

    @Override
    public Entrega criarEntrega(Pedido pedido, Entregador entregador) {
        Entrega entrega = new Entrega();
        entrega.setPedido(pedido);
        entrega.marcarAtribuida();
        entrega.setEntregador(entregador);
        return entrega;
    }

}
