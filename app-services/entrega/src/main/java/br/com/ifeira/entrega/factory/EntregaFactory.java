package br.com.Ifeira.entrega.factory;

import br.com.Ifeira.compra.entity.shared.Pedido;
import br.com.Ifeira.compra.entity.shared.Pessoa;
import br.com.Ifeira.entrega.entity.Entrega;

public interface EntregaFactory {
    public Entrega criarEntrega(Pedido pedido, Pessoa cliente, Pessoa entregador);
}
