package br.com.ifeira.entrega.factories;

import br.com.ifeira.compra.shared.entity.Pedido;
import br.com.ifeira.entrega.entity.Entrega;
import br.com.ifeira.entrega.entity.Entregador;

public interface EntregaFactory {

    Entrega criarEntrega(Pedido pedido, Entregador entregador);

}
