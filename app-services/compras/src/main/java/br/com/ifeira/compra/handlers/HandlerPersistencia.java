package br.com.ifeira.compra.handlers;

import br.com.ifeira.compra.entity.Pagamento;
import br.com.ifeira.compra.entity.PagamentoProdutor;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import org.springframework.jdbc.core.JdbcTemplate;

public class HandlerPersistencia extends PagamentoInBaseHandler {

    public HandlerPersistencia(JdbcTemplate jdbcTemplate, PagamentoProdutor pagamentoProdutor) {

    }

    @Override
    public PagamentoDTO handle(Pagamento pagamento) {
        return null;
    }


}
