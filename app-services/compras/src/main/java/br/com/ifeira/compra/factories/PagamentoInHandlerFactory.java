package br.com.ifeira.compra.factories;

import br.com.ifeira.compra.handlers.PagamentoInHandler;
import org.springframework.jdbc.core.JdbcTemplate;

public interface PagamentoInHandlerFactory {
    public PagamentoInHandler criarPagamentoInChain(JdbcTemplate jdbcTemplate);
}
