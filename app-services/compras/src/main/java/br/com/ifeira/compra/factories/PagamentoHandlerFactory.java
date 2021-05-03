package br.com.ifeira.compra.factories;

import br.com.ifeira.compra.entity.PagamentoProdutor;
import org.springframework.jdbc.core.JdbcTemplate;

public interface PagamentoHandlerFactory {
    public PagamentoFactory criarPagamentoChain(JdbcTemplate jdbcTemplate, PagamentoProdutor pagamentoProdutor);

}
