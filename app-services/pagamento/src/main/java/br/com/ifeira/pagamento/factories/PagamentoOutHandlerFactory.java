package br.com.ifeira.pagamento.factories;

import br.com.ifeira.pagamento.config.APIConfig;
import br.com.ifeira.pagamento.handlers.PagamentoOutHandler;
import org.springframework.web.client.RestTemplate;

public interface PagamentoOutHandlerFactory {
    PagamentoOutHandler criarPagamentoOutChain(APIConfig apiConfig, RestTemplate restTemplate);
}
