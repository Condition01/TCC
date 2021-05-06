package br.com.ifeira.compra.shared.dao;

import br.com.ifeira.compra.shared.entity.Pedido;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class PedidoDAO implements Persistivel<Pedido, Long> {

    private JdbcTemplate jdbcTemplate;

    public PedidoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Pedido salvar(Pedido item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql
                = "INSERT INTO PEDIDO\n" +
                "(DATA_PEDIDO, STATUS_PEDIDO, DATA_ENTREGA, CUPOM, CPF_PESSOA, COBRANCA, VALOR_TOTAL)\n" +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, new Date(item.getData().getTime()));
            ps.setString(2, item.getStatusPedido().name());
            ps.setDate(3, new Date(item.getDataEntrega().getTime()));
            ps.setString(4, item.getCupom().getNome());
            ps.setString(5, item.getCliente().getCpf());
            ps.setString(6, item.getCobranca());
            ps.setDouble(7, item.getValorTotal());
            return ps;
        }, keyHolder);

        Long primaryKey = keyHolder.getKey().longValue();
        item.setNumeroPedido(primaryKey);
        return item;
    }

    @Override
    public List<Pedido> listar() {
        return null;
    }

    @Override
    public Pedido buscar(Long key) {

        return null;

    }

    @Override
    public List<Pedido> buscarMultiplos(Long key) {
        return null;
    }

    @Override
    public Pedido editar(Pedido item) {
        return null;
    }

}
