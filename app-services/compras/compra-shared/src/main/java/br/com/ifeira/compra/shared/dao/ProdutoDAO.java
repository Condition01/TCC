package br.com.ifeira.compra.shared.dao;

import br.com.ifeira.compra.shared.entity.Produto;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProdutoDAO implements Persistivel<Produto, String> {

    private JdbcTemplate jdbcTemplate;

    public ProdutoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Produto salvar(Produto item) {
        return null;
    }

    @Override
    public List<Produto> listar() {
        return null;
    }

    @Override
    public Produto buscar(String key) {
        return null;
    }

    @Override
    public Produto editar(Produto item) {
        return null;
    }
}
