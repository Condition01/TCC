package br.com.ifeira.compra.shared.dao;

import br.com.ifeira.compra.shared.entity.Carrinho;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CarrinhoDAO implements PersistivelContextual<Carrinho, Pair<Long, String>> {

    private JdbcTemplate jdbcTemplate;

    public CarrinhoDAO(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Carrinho salvar(Carrinho item, String contexto) {
        return null;
    }

    @Override
    public List<Carrinho> listar(String contexto) {
        return null;
    }

    @Override
    public Carrinho buscar(Pair<Long, String> key, String contexto) {
        return null;
    }

    @Override
    public List<Carrinho> buscarMultiplos(Pair<Long, String> key, String contexto) {
        return null;
    }

    @Override
    public Carrinho editar(Carrinho item) {
        return null;
    }

}
