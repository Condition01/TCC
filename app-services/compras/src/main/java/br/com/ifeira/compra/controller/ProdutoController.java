package br.com.ifeira.compra.controller;

import br.com.ifeira.compra.shared.dao.PersistivelContextual;
import br.com.ifeira.compra.shared.dao.ProdutoFeiraDAO;
import br.com.ifeira.compra.shared.entity.ProdutoFeira;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutoController {

    private PersistivelContextual<ProdutoFeira, String> produtoDAO;

    public ProdutoController(@Autowired JdbcTemplate jdbcTemplate) {
        this.produtoDAO = new ProdutoFeiraDAO(jdbcTemplate);
    }

    public List<ProdutoFeira> listarFLV(String contexto){
        return this.produtoDAO.listar(contexto);
    }

    public List<ProdutoFeira> buscarFLVS(String nome, String contexto){
        nome += "%";
        return this.produtoDAO.buscarMultiplos(nome, contexto);
    }

}
