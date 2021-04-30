package br.com.ifeira.compra.shared.dao;

import br.com.ifeira.compra.shared.entity.Pessoa;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PessoaDAO implements Persistivel<Pessoa, Integer>{

    private JdbcTemplate jdbcTemplate;

    public PessoaDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Pessoa salvar(Pessoa item) {
        return null;
    }

    @Override
    public List<Pessoa> listar() {
        return null;
    }

    @Override
    public Pessoa buscar(Integer key) {
        return null;
    }

    @Override
    public List<Pessoa> buscarMultiplos(Integer key) {
        return null;
    }

    @Override
    public Pessoa editar(Pessoa obj) {
        return null;
    }

}
