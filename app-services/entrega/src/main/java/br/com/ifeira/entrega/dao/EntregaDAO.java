package br.com.ifeira.entrega.dao;

import br.com.ifeira.compra.shared.dao.Persistivel;
import br.com.ifeira.entrega.entity.Entrega;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class EntregaDAO implements Persistivel<Entrega, Long> {

    private JdbcTemplate jdbcTemplate;

    public EntregaDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Entrega salvar(Entrega item) {
        return null;
    }

    @Override
    public List<Entrega> listar() {
        return null;
    }

    @Override
    public Entrega buscar(Long identificador) {
        return null;
    }

    @Override
    public List<Entrega> buscarMultiplos(Long key) {
        return null;
    }

    @Override
    public Entrega editar(Entrega item) {
        return null;
    }

    @Override
    public List<Entrega> buscarComParametros(Object[] params) {
        return null;
    }
}
