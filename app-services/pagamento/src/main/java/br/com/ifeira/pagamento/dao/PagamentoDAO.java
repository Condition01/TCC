package br.com.ifeira.pagamento.dao;

import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

public class PagamentoDAO {

    private JdbcTemplate jdbcTemplate;

    public PagamentoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean verificarCancelamento(PagamentoDTO pagamento) {
        String sql = "SELECT p.STATUS_PEDIDO FROM PEDIDO p WHERE p.NUMERO = ?";

        return jdbcTemplate.query(sql, ps -> {
            ps.setLong(1, pagamento.getNumeroPedido());
        }, rs -> {

            Boolean cancelado = false;

            while (rs.next()) {
                String statusPedido = rs.getString("STATUS_PEDIDO");

                if(statusPedido.equals("CANCELADO")) {
                    cancelado = true;
                }
            }

            return cancelado;
        });
    }

    @Transactional
    public void persistirPagamentosComErro(PagamentoDTO pagamento) {
        String statusPagamento = "CANCELADO";
        String statusPedido = "CANCELADO";

        if(pagamento.getNumeroPedido() != null && pagamento.getNumeroCartao() != null) {
            this.jdbcTemplate.update(
                    "UPDATE PAGAMENTO pa SET pa.STATUS_PAGAMENTO = ? WHERE pa.ID = ?", statusPagamento, pagamento.getIdPagamento());

            this.jdbcTemplate.update("UPDATE PEDIDO p SET p.STATUS_PEDIDO = ? WHERE p.NUMERO = ?", statusPedido, pagamento.getNumeroPedido());
        }
    }

    @Transactional
    public void persistirPagamentosComSucesso(PagamentoDTO pagamento) {
        String statusPagamento = "CONFIRMADO";

        String statusPedido = "PAGO";

        if(pagamento.getNumeroPedido() != null && pagamento.getNumeroCartao() != null) {
            Date dataPagamento = pagamento.getData() != null ? new Date(pagamento.getData().getTime()) : null;

            this.jdbcTemplate.update(
                    "UPDATE PAGAMENTO pa SET pa.STATUS_PAGAMENTO = ?, pa.DATA_PAGAMENTO = ? WHERE pa.ID = ?", statusPagamento, dataPagamento, pagamento.getIdPagamento());

            this.jdbcTemplate.update("UPDATE PEDIDO p SET p.STATUS_PEDIDO = ? WHERE p.NUMERO = ?", statusPedido, pagamento.getNumeroPedido());
        }
    }

}
