package br.com.ifeira.pagamento.factories;

import br.com.ifeira.pagamento.config.APIConfig;
import br.com.ifeira.pagamento.config.QueueConfig;
import br.com.ifeira.pagamento.handlers.HandlerDecriptacao;
import br.com.ifeira.pagamento.handlers.HandlerLogs;
import br.com.ifeira.pagamento.handlers.HandlerPagamento;
import br.com.ifeira.pagamento.handlers.PagamentoOutHandler;

public class PagamentoOutConcretHandlerFactory implements PagamentoOutHandlerFactory {
    @Override
    public PagamentoOutHandler criarPagamentoOutChain(APIConfig apiConfig) {
        PagamentoOutHandler handlerLogs = new HandlerLogs();
        PagamentoOutHandler handlerDecriptacao = new HandlerDecriptacao();
        HandlerPagamento handlerPagamento = new HandlerPagamento();
        handlerPagamento.setApiConfig(apiConfig);

        handlerLogs.setarProximo(handlerDecriptacao);
        handlerDecriptacao.setarProximo(handlerPagamento);

        return handlerLogs;
    }
}
