package br.com.ifeira.entrega.dao;

import br.com.ifeira.compra.shared.dao.PedidoDAO;
import br.com.ifeira.compra.shared.dao.Persistivel;
import br.com.ifeira.compra.shared.entity.Pedido;
import br.com.ifeira.entrega.entity.Entrega;
import br.com.ifeira.entrega.entity.Entregador;
import br.com.ifeira.entrega.enums.StatusEntrega;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EntregaDAO implements Persistivel<Entrega, Long> {

    private JdbcTemplate jdbcTemplate;
    private Persistivel<Pedido, Long> pedidoDAO;
    private Persistivel<Entregador, String> entregadorDAO;

    public EntregaDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.pedidoDAO = new PedidoDAO(jdbcTemplate);
        this.entregadorDAO = new EntregadorDAO(jdbcTemplate);
    }

    @Override
    public Entrega salvar(Entrega item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql
                = "INSERT INTO ENTREGA\n" +
                "(CPF_ENTREGADOR, NUMERO_PEDIDO, STATUS_ENTREGA)\n" +
                "VALUES(?, ?, ?);\n";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getEntregador().getCpf());
            ps.setLong(2, item.getPedido().getNumeroPedido());
            ps.setString(3, item.getStatusEntrega().getValue());
            return ps;
        }, keyHolder);

        Long primaryKey = keyHolder.getKey().longValue();
        item.setId(primaryKey);
        return item;
    }

    @Override
    public List<Entrega> listar() {
        String sql = "SELECT\n" +
                "\te.ID,\n" +
                "\te.CPF_ENTREGADOR,\n" +
                "\te.NUMERO_PEDIDO,\n" +
                "\te.DATA_REALIZACAO,\n" +
                "\te.STATUS_ENTREGA\n" +
                "FROM\n" +
                "\tENTREGA e";

        return jdbcTemplate.query(sql, rs -> {
            List<Entrega> entregas = new ArrayList<>();

            while (rs.next()) {
                Entrega entrega = new Entrega();
                entrega.setId(rs.getLong("ID"));
                entrega.setEntregador(this.entregadorDAO.buscar(rs.getString("CPF_ENTREGADOR")));
                entrega.setPedido(this.pedidoDAO.buscar(rs.getLong("NUMERO_PEDIDO")));
                entrega.setDataRealizacao(rs.getDate("DATA_REALIZACAO"));
                entrega.setStatusEntrega(StatusEntrega.valueOf(rs.getString("STATUS_ENTREGA")));

                entregas.add(entrega);
            }

            return entregas;
        });
    }

    @Override
    public Entrega buscar(Long identificador) {
        String sql = "SELECT\n" +
                "\te.ID,\n" +
                "\te.CPF_ENTREGADOR,\n" +
                "\te.NUMERO_PEDIDO,\n" +
                "\te.DATA_REALIZACAO,\n" +
                "\te.STATUS_ENTREGA,\n" +
                "\tp.EMAIL\n" +
                "\tFROM\n" +
                "\tENTREGA e " +
                "\tINNER JOIN PESSOA p ON p.CPF = e.CPF_ENTREGADOR\n" +
                "\tWHERE e.ID = ?";

        return jdbcTemplate.query(sql, ps -> {
            ps.setLong(1, identificador);
        }, rs -> {
            Entrega entrega = null;

            while (rs.next()) {
                entrega = new Entrega();
                entrega.setId(rs.getLong("ID"));
                entrega.setEntregador(this.entregadorDAO.buscar(rs.getString("EMAIL")));
                entrega.setPedido(this.pedidoDAO.buscar(rs.getLong("NUMERO_PEDIDO")));
                entrega.setDataRealizacao(rs.getDate("DATA_REALIZACAO"));
                entrega.setStatusEntrega(StatusEntrega.valueOf(rs.getString("STATUS_ENTREGA")));
            }

            return entrega;
        });
    }

    @Override
    public List<Entrega> buscarMultiplos(Long key) {
        return null;
    }

    @Override
    public Entrega editar(Entrega item) {
        String sql = "UPDATE ENTREGA e SET e.STATUS_ENTREGA = ?, e.DATA_REALIZACAO = ? WHERE e.NUMERO_PEDIDO = ?";
        this.jdbcTemplate.update(sql, ps -> {
            ps.setString(1, item.getStatusEntrega().name());
            ps.setDate(2, new Date(item.getDataRealizacao().getTime()));
            ps.setLong(3, item.getPedido().getNumeroPedido());
        });
        return item;
    }

    @Override
    public List<Entrega> buscarComParametros(Object[] params) {
        StatusEntrega status = (StatusEntrega) params[0];
        Entregador entregador = (Entregador) params[1];

        String sql = "SELECT\n" +
                "\te.ID,\n" +
                "\te.CPF_ENTREGADOR,\n" +
                "\te.NUMERO_PEDIDO,\n" +
                "\te.DATA_REALIZACAO,\n" +
                "\te.STATUS_ENTREGA\n" +
                "FROM\n" +
                "\tENTREGA e \n";

        PreparedStatementSetter ps;
        String WHERE = "";

        if(status != null) {
            WHERE = "WHERE e.CPF_ENTREGADOR = ? and e.STATUS_ENTREGA = ?";
            ps = new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setString(1, entregador.getCpf());
                    ps.setString(2, status.name());
                }
            };
        } else {
            WHERE = "WHERE e.CPF_ENTREGADOR = ?";
            ps = new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setString(1, entregador.getCpf());
                }
            };
        }

        sql += WHERE;

        return jdbcTemplate.query(sql, ps, rs -> {
            List<Entrega> entregas = new ArrayList<>();

            while (rs.next()) {
                Entrega entrega = new Entrega();
                entrega.setId(rs.getLong("ID"));
                entrega.setEntregador(entregador);
                entrega.setPedido(this.pedidoDAO.buscar(rs.getLong("NUMERO_PEDIDO")));
                entrega.setDataRealizacao(rs.getDate("DATA_REALIZACAO"));
                entrega.setStatusEntrega(StatusEntrega.valueOf(rs.getString("STATUS_ENTREGA")));
                entregas.add(entrega);
            }

            return entregas;
        });
    }

    public List<Entrega> buscarEntregasAtrasadas() {
        java.util.Date truncatedDate = DateUtils.truncate(new java.util.Date(), Calendar.DATE);


        String sql = "SELECT\n" +
                "\te.ID,\n" +
                "\te.CPF_ENTREGADOR,\n" +
                "\te.NUMERO_PEDIDO,\n" +
                "\te.DATA_REALIZACAO,\n" +
                "\te.STATUS_ENTREGA\n" +
                "FROM\n" +
                "\tENTREGA e\n" +
                "INNER JOIN PEDIDO p ON\n" +
                "\te.NUMERO_PEDIDO = p.NUMERO\n" +
                "WHERE\n" +
                "\te.STATUS_ENTREGA = ?\n" +
                "\tAND p.DATA_ENTREGA < ?";

        return jdbcTemplate.query(sql, ps -> {
            ps.setString(1, StatusEntrega.ATRIBUIDA.name());
            ps.setDate(2, new Date(truncatedDate.getTime()));
        }, rs -> {
            List<Entrega> entregas = new ArrayList<>();


            while (rs.next()) {
                Entrega entrega = new Entrega();
                entrega.setId(rs.getLong("ID"));
                entrega.setEntregador(this.entregadorDAO.buscar(rs.getString("CPF_ENTREGADOR")));
                entrega.setPedido(this.pedidoDAO.buscar(rs.getLong("NUMERO_PEDIDO")));
                entrega.setDataRealizacao(rs.getDate("DATA_REALIZACAO"));
                entrega.setStatusEntrega(StatusEntrega.valueOf(rs.getString("STATUS_ENTREGA")));

                entregas.add(entrega);
            }

            return entregas;
        });
    }

}
