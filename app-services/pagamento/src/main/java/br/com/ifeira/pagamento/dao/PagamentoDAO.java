package br.com.ifeira.pagamento.dao;

import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import org.springframework.jdbc.core.JdbcTemplate;

public class PagamentoDAO {

    private JdbcTemplate jdbcTemplate;

    public PagamentoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean verificarCancelamento(PagamentoDTO pagamento) {
        return false;
    }

    public void persistirPagamentosComErro(PagamentoDTO pagamento) {

    }

    public void persistirPagamentosComSucesso(PagamentoDTO pagamento) {

    }

}
