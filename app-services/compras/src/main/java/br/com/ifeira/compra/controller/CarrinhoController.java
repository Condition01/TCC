package br.com.ifeira.compra.controller;

import br.com.ifeira.compra.shared.dao.PersistivelContextual;
import br.com.ifeira.compra.shared.dao.ProdutoFeiraDAO;
import br.com.ifeira.compra.shared.entity.Carrinho;
import br.com.ifeira.compra.shared.entity.ProdutoFeira;
import br.com.ifeira.compra.shared.entity.ProdutoQuantidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CarrinhoController {

    PersistivelContextual<ProdutoFeira, String> produtoDAO;

    public CarrinhoController(@Autowired JdbcTemplate jdbcTemplate) {
        this.produtoDAO = new ProdutoFeiraDAO(jdbcTemplate);
    }

    public Carrinho recuperarCarrinho(Map<String, Integer> prodQtdMap, String contexto){
        List<ProdutoQuantidade> prodsQtd = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : prodQtdMap.entrySet()) {
            ProdutoFeira produtoFeira = this.produtoDAO.buscar(entry.getKey(), contexto);
            ProdutoQuantidade produtoQuantidade = new ProdutoQuantidade();
            produtoQuantidade.setProdutoFeira(produtoFeira);
            produtoQuantidade.setQuantidade(entry.getValue());
            prodsQtd.add(produtoQuantidade);
        }
        Carrinho carrinho = new Carrinho();
        carrinho.setListaProdutoQuantidade(prodsQtd);
        return carrinho;
    }

}
