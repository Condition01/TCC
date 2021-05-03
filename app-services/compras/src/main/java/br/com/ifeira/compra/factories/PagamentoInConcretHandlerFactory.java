package br.com.ifeira.compra.factories;


import br.com.ifeira.compra.entity.PagamentoProdutor;
import br.com.ifeira.compra.handlers.*;
import org.springframework.jdbc.core.JdbcTemplate;

public class PagamentoInConcretHandlerFactory implements PagamentoInHandlerFactory {

    @Override
    public PagamentoInHandler criarPagamentoInChain(JdbcTemplate jdbcTemplate, PagamentoProdutor pagamentoProdutor) {
        PagamentoInHandler handlerLogs = new HandlerLogs();
        PagamentoInHandler handlerDecriptacao = new HandlerCriptografia();
        PagamentoInHandler handlerValidacao = new HandlerValidacao(jdbcTemplate);
        PagamentoInHandler handlerPersistencia = new HandlerPersistencia(jdbcTemplate, pagamentoProdutor);

        handlerLogs.setNext(handlerDecriptacao);
        handlerDecriptacao.setNext(handlerValidacao);
        handlerValidacao.setNext(handlerPersistencia);

        return handlerLogs;
    }

}
