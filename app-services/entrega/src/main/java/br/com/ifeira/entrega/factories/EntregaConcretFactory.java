package br.com.ifeira.entrega.factories;

import br.com.ifeira.compra.shared.entity.Pedido;
import br.com.ifeira.compra.shared.entity.Pessoa;
import br.com.ifeira.entrega.entity.Entrega;

public class EntregaConcretFactory implements EntregaFactory {

    @Override
    public Entrega criarEntrega(Pedido pedido, Pessoa entregador) {
        Entrega entrega = new Entrega();
        entrega.setEntregador(entregador);
        return null;
    }

}
