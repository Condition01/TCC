package br.com.ifeira.pagamento.factories;

import br.com.ifeira.pagamento.handlers.HandlerDecriptacao;
import br.com.ifeira.pagamento.handlers.HandlerLogs;
import br.com.ifeira.pagamento.handlers.HandlerPagamento;
import br.com.ifeira.pagamento.handlers.PagamentoOutHandler;

public class PagamentoOutConcretHandlerFactory implements PagamentoOutHandlerFactory {
    @Override
    public PagamentoOutHandler criarPagamentoOutChain() {
        PagamentoOutHandler handlerLogs = new HandlerLogs();
        PagamentoOutHandler handlerDecriptacao = new HandlerDecriptacao();
        PagamentoOutHandler handlerPagamento = new HandlerPagamento();

        handlerLogs.setNext(handlerDecriptacao);
        handlerDecriptacao.setNext(handlerPagamento);

        return handlerLogs;
    }
}
