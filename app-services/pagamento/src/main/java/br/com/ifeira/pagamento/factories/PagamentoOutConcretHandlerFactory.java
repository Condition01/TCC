package br.com.ifeira.pagamento.factories;

import br.com.ifeira.pagamento.config.APIConfig;
import br.com.ifeira.pagamento.handlers.HandlerDecriptacao;
import br.com.ifeira.pagamento.handlers.HandlerLogs;
import br.com.ifeira.pagamento.handlers.HandlerPagamento;
import br.com.ifeira.pagamento.handlers.PagamentoOutHandler;
import org.springframework.web.client.RestTemplate;

public class PagamentoOutConcretHandlerFactory implements PagamentoOutHandlerFactory {
    @Override
    public PagamentoOutHandler criarPagamentoOutChain(APIConfig apiConfig, RestTemplate restTemplate) {
        PagamentoOutHandler handlerLogs = new HandlerLogs();
        PagamentoOutHandler handlerDecriptacao = new HandlerDecriptacao();
        PagamentoOutHandler handlerPagamento = new HandlerPagamento(apiConfig, restTemplate);

        handlerLogs.setarProximo(handlerDecriptacao);
        handlerDecriptacao.setarProximo(handlerPagamento);

        return handlerLogs;
    }
}
