package br.com.ifeira.compra.shared.dao;

import br.com.ifeira.compra.shared.entity.Endereco;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class EnderecoDAO implements Persistivel<Endereco, Integer>{

    private JdbcTemplate jdbcTemplate;

    public EnderecoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Endereco salvar(Endereco item) {
        return null;
    }

    @Override
    public List<Endereco> listar() {
        return null;
    }

    @Override
    public Endereco buscar(Integer key) {
        return null;
    }

    @Override
    public Endereco editar(Endereco item) {
        return null;
    }
}
