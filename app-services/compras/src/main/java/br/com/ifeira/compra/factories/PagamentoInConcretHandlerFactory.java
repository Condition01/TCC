package br.com.ifeira.compra.factories;


import br.com.ifeira.compra.entity.PagamentoProdutor;
import br.com.ifeira.compra.handlers.*;
import org.springframework.jdbc.core.JdbcTemplate;

public class PagamentoInConcretHandlerFactory implements PagamentoInHandlerFactory {

    @Override
    public PagamentoInHandler criarPagamentoInChain(JdbcTemplate jdbcTemplate) {
        PagamentoInHandler handlerLogs = new HandlerLogs();
        PagamentoInHandler handlerCriptografia = new HandlerCriptografia();
        PagamentoInHandler handlerValidacao = new HandlerValidacao(jdbcTemplate);

        handlerLogs.setNext(handlerValidacao);
        handlerValidacao.setNext(handlerCriptografia);

        return handlerLogs;
    }

}
