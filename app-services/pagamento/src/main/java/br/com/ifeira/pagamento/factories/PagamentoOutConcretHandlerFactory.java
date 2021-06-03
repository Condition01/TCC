package br.com.ifeira.pagamento.factories;

import br.com.ifeira.pagamento.config.APIConfig;
import br.com.ifeira.pagamento.handlers.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

public class PagamentoOutConcretHandlerFactory implements PagamentoOutHandlerFactory {
    @Override
    public PagamentoOutHandler criarPagamentoOutChain(JdbcTemplate jdbcTemplate) {
        PagamentoOutHandler handlerLogs = new HandlerLogs();
        PagamentoOutHandler handlerDecriptacao = new HandlerDecriptacao();
        PagamentoOutHandler handlerValidacao = new HandlerValidacao(jdbcTemplate);

        handlerLogs.setarProximo(handlerValidacao);
        handlerValidacao.setarProximo(handlerDecriptacao);

        return handlerLogs;
    }
}
