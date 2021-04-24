package br.com.ifeira.pagamento.factories;

import br.com.ifeira.pagamento.config.APIConfig;
import br.com.ifeira.pagamento.handlers.PagamentoOutHandler;

public interface PagamentoOutHandlerFactory {
    PagamentoOutHandler criarPagamentoOutChain(APIConfig apiConfig);
}
