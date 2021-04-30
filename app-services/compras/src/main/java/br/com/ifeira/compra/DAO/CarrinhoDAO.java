package br.com.ifeira.compra.dao;

import br.com.ifeira.compra.shared.dao.Persistivel;
import br.com.ifeira.compra.shared.entity.Carrinho;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CarrinhoDAO implements Persistivel<Carrinho, Integer> {

    private JdbcTemplate jdbcTemplate;

    public CarrinhoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Carrinho salvar(Carrinho item) {
        return null;
    }

    @Override
    public List<Carrinho> listar() {
        return null;
    }

    @Override
    public Carrinho buscar(Integer key) {
        return null;
    }

    @Override
    public List<Carrinho> buscarMultiplos(Integer key) {
        return null;
    }

    @Override
    public Carrinho editar(Carrinho obj) {
        return null;
    }

}
