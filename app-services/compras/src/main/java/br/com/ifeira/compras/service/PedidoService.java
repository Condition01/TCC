package br.com.ifeira.compras.service;

import br.com.ifeira.compras.dao.PagamentoDAO;
import br.com.ifeira.compras.dao.PedidoDAO;
import br.com.ifeira.compras.dao.ProdutoDAO;
import br.com.ifeira.compras.dao.UsuarioDAO;
import br.com.ifeira.compras.model.*;
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

    @Autowired
    private ProdutoDAO produtoDAO;

    public Pedido criarPedido(Pedido pedido) throws Exception {
        Optional<Usuario> optCliente = this.usuarioDAO.findById(pedido.getCliente().getCpf());
        List<ProdutoQuantidade> listaProdutoQuantidade = new ArrayList<>();

        if (optCliente.isPresent()) {
            pedido.getPagamento().setPedido(pedido);
            Usuario cliente = optCliente.get();
            pedido.setCliente(cliente);
            cliente.adicionarPedidos(pedido);
            listaProdutoQuantidade = pedido.getListaProdutos();
            resolverDependenciasProduto(listaProdutoQuantidade);
            return this.pedidoDAO.save(pedido);
        } else {
            throw new Exception("Cliente não esta presente!!");
        }
    }

    private void resolverDependenciasProduto(List<ProdutoQuantidade> listaProdutoQuantidade) throws Exception {
        for (ProdutoQuantidade pqtd : listaProdutoQuantidade) {
            Produto produtoAchado = pqtd.getProduto();
            Optional<Produto> optProduto = this.produtoDAO.findById(produtoAchado.getCodProduto());
            if(optProduto.isPresent()){
                produtoAchado = optProduto.get();
                produtoAchado.adicionarProdutoQuantidade(pqtd);
                pqtd.setProduto(produtoAchado);
            }else {
                throw new Exception("Produto código: " + produtoAchado.getCodProduto() + " não encontrado");
            }
        }
    }

    public List<Pedido> listarPedidos() {
        return this.pedidoDAO.findAll();
    }

    public Pedido acharPedido(Long numero) throws Exception {
        Optional<Pedido> optPedido = this.pedidoDAO.acharPedido(numero);
        if (optPedido.isPresent()) {
            return optPedido.get();
        } else {
            throw new Exception("Não existe nenhum pedido com esse numero");
        }
    }
}
