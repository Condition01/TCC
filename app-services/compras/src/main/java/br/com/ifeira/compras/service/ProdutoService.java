package br.com.ifeira.compras.service;

import br.com.ifeira.compras.dao.ProdutoDAO;
import br.com.ifeira.compras.model.Produto;
import br.com.ifeira.compras.model.ProdutoQuantidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoDAO produtoDAO;


    public List<Produto> listarProduto(){
        return produtoDAO.findAll();
    }

    public Optional<Produto> selecionarProduto(Long numero){
        return produtoDAO.findById(numero);
    }

    public Produto deletarProduto(Long cod_produto){
        Optional<Produto> optProduto = produtoDAO.findById(cod_produto);
        if(optProduto.isPresent()){
            produtoDAO.delete(optProduto.get());
        }
        return optProduto.get();
    }

    public void inserirProduto(Produto produto){
        produtoDAO.save(produto);
    }

    public List<ProdutoQuantidade> getListaProduto(Map<Long, Integer> qtdProdutos)
    {
        ProdutoQuantidade produtoQtd;
        List produtoQuantidade = new ArrayList();

        for (Map.Entry<Long, Integer> qtdProduto: qtdProdutos.entrySet()) {
            produtoQtd = new ProdutoQuantidade();
            Produto produto = produtoDAO.findById(qtdProduto.getKey()).get();
            produtoQtd.setProduto(produto);
            produtoQtd.setQuantidade(qtdProduto.getValue());
            produtoQuantidade.add(produtoQtd);
        }
        return produtoQuantidade;
    }
}
