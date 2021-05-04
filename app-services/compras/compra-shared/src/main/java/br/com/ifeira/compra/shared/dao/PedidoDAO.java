package br.com.ifeira.compra.shared.dao;

import br.com.ifeira.compra.shared.entity.Pedido;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Date;
import java.sql.PreparedStatement;
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
                "(DATA_PEDIDO, STATUS_PEDIDO, DATA_ENTREGA, CUPOM, CPF_PESSOA, COD_PRODUTO, CONTEXTO_FEIRA, COBRANCA, VALOR_TOTAL)\n" +
                "VALUES('', '', '', '', '', '', '', '', 0);\n";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql);
            ps.setDate(1, new Date(item.getData().getTime()));
            ps.setString(2, item.getStatusPedido().name());
            ps.setDate(3, new Date(item.getDataEntrega().getTime()));
            ps.setString(5, item.getCupom().getNome());
            ps.setString(6, item.getCliente().getCpf());
//            ps.setString(item.getCarrinho().getListaProdutoQuantidade().get(0).getProdutoFeira().getFeira().);
            ps.setString(7, item.getCarrinho().getListaProdutoQuantidade().get(0).getProdutoFeira().getFeira().getContext());
            ps.setString(8, item.getCobranca());
            ps.setDouble(9, item.getValorTotal());
            return ps;
        }, keyHolder);


        Long primaryKey = keyHolder.getKey().longValue();
        return this.buscar(primaryKey);
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
