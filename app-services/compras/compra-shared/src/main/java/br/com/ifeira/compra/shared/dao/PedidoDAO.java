package br.com.ifeira.compra.shared.dao;

import br.com.ifeira.compra.shared.entity.Pedido;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PedidoDAO implements Persistivel<Pedido, String> {

    private JdbcTemplate jdbcTemplate;

    public PedidoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Pedido salvar(Pedido item) {
        return null;
    }

    @Override
    public List<Pedido> listar() {
        return null;
    }

    @Override
    public Pedido buscar(String key) {
        return null;
    }

    @Override
    public Pedido editar(Pedido item) {
        return null;
    }

}
