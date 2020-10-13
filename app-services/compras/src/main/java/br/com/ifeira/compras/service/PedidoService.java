package br.com.ifeira.compras.service;

import br.com.ifeira.compras.dao.PedidoDAO;
import br.com.ifeira.compras.dao.UsuarioDAO;
import br.com.ifeira.compras.model.Pedido;
import br.com.ifeira.compras.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoDAO pedidoDAO;

    @Autowired
    private UsuarioDAO usuarioDAO;

    public Pedido criarPedido(Pedido pedido) throws Exception {
        Optional<Usuario> optCliente = this.usuarioDAO.findById(pedido.getCliente().getCpf());
        Optional<Usuario> optFeirante = this.usuarioDAO.findById(pedido.getFeirante().getCpf());
        if(optCliente.isPresent() && optFeirante.isPresent()){
            Usuario cliente = optCliente.get();
            Usuario feirante = optFeirante.get();
            pedido.setCliente(cliente);
            pedido.setFeirante(feirante);
            return this.pedidoDAO.save(pedido);
        }else{
            throw new Exception("Cliente ou Feirante não existe");
        }

    }

    public List<Pedido> listarPedidos() {
        return this.pedidoDAO.findAll();
    }
 }
