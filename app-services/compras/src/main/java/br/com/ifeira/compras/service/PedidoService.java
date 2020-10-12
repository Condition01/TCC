package br.com.ifeira.compras.service;

import br.com.ifeira.compras.dao.PedidoDAO;
import br.com.ifeira.compras.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoDAO pedidoDAO;

    public Pedido criarPedido(Pedido pedido){
        return this.pedidoDAO.save(pedido);
    }
}
