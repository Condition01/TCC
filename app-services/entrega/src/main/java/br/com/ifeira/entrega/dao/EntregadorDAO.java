package br.com.ifeira.entrega.dao;

import br.com.ifeira.compra.shared.dao.PersistivelContextual;
import br.com.ifeira.entrega.entity.Entregador;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class EntregadorDAO implements PersistivelContextual<Entregador, Long> {

    private JdbcTemplate jdbcTemplate;

    public EntregadorDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Entregador salvar(Entregador item, String contexto) {
        return null;
    }

    @Override
    public List<Entregador> listar(String contexto) {
        return null;
    }

    @Override
    public Entregador buscar(Long key, String contexto) {
        return null;
    }

    @Override
    public List<Entregador> buscarMultiplos(Long key, String contexto) {
        return null;
    }

    @Override
    public Entregador editar(Entregador item) {
        return null;
    }
}
