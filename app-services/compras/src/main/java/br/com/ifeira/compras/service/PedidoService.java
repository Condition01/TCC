package br.com.ifeira.compras.service;

import br.com.ifeira.compras.dao.PagamentoDAO;
import br.com.ifeira.compras.dao.PedidoDAO;
import br.com.ifeira.compras.dao.UsuarioDAO;
import br.com.ifeira.compras.model.Pagamento;
import br.com.ifeira.compras.model.Pedido;
import br.com.ifeira.compras.model.ProdutoQuantidade;
import br.com.ifeira.compras.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<ProdutoQuantidade> listaProdutoQuantidade = new ArrayList<>();

        if (optCliente.isPresent()) {
            pedido.getPagamento().setPedido(pedido);
            Usuario cliente = optCliente.get();
            pedido.setCliente(cliente);
            cliente.adicionarPedidos(pedido);
            listaProdutoQuantidade = pedido.getListaProdutos();
            for (ProdutoQuantidade pqtd: listaProdutoQuantidade) {
                 pqtd.getProduto().setProdutoQuantidade(pqtd);
            }
            return this.pedidoDAO.save(pedido);
        } else {
            throw new Exception("Cliente não esta presente!!");
        }
    }



    public List<Pedido> listarPedidos() {
        return this.pedidoDAO.findAll();
    }

    public Pedido acharPedido(Long numero) throws Exception {
        Optional<Pedido> optPedido = this.pedidoDAO.acharPedido(numero);
        if(optPedido.isPresent()){
            return optPedido.get();
        }else{
            throw new Exception("Não existe nenhum pedido com esse numero");
        }
    }
}
