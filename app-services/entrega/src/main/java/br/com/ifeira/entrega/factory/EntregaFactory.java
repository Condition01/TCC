package br.com.ifeira.entrega.factory;

import br.com.ifeira.compra.shared.entity.Pedido;
import br.com.ifeira.compra.shared.entity.Pessoa;
import br.com.ifeira.entrega.entity.Entrega;

public interface EntregaFactory {
    public Entrega criarEntrega(Pedido pedido, Pessoa cliente, Pessoa entregador);
}
