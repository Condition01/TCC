package br.com.ifeira.compra.shared.dao;

import br.com.ifeira.compra.shared.entity.Feira;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class FeiraDAO implements Persistivel<Feira, Integer> {

    private JdbcTemplate jdbcTemplate;

    public FeiraDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Feira salvar(Feira item) {
        return null;
    }

    @Override
    public List<Feira> listar() {
        return null;
    }

    @Override
    public Feira buscar(Integer key) {
        return null;
    }

    @Override
    public Feira editar(Feira item) {
        return null;
    }

}
