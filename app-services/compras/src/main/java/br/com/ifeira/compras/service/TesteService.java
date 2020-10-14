package br.com.ifeira.compras.service;

import br.com.ifeira.compras.dao.*;
import br.com.ifeira.compras.model.Pagamento;
import br.com.ifeira.compras.model.Reclamacao;
import br.com.ifeira.compras.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TesteService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private PagamentoDAO pagamentoDAO;

    @Autowired
    private EntregaDAO entregaDAO;

//    @Autowired
//    private Reclamacao reclamacaoDAO;

    @Autowired
    private ProdutoDAO produtoDAO;

    @Autowired
    private PedidoDAO pedidoDAO;

    public List<Pagamento> getPagamento() {
        return this.pagamentoDAO.findAll();
    }

    public List<Usuario> getClients() { return this.usuarioDAO.findAll(); }

}
