package br.com.Ifeira.entrega.factory;

import br.com.Ifeira.compra.entity.shared.Pedido;
import br.com.Ifeira.compra.entity.shared.Pessoa;
import br.com.Ifeira.entrega.entity.Entrega;

 public class EntregaSimplesFactory implements EntregaFactory {

     @Override
     public Entrega criarEntrega(Pedido pedido, Pessoa cliente, Pessoa entregador) {
         return null;
     }
 }
