package br.com.ifeira.pagamento.factories;

import br.com.ifeira.pagamento.handlers.PagamentoOutHandler;
import org.springframework.jdbc.core.JdbcTemplate;

public interface PagamentoOutHandlerFactory {
    PagamentoOutHandler criarPagamentoOutChain(JdbcTemplate jdbcTemplate);
}
