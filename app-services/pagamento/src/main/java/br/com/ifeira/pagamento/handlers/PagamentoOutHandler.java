package br.com.ifeira.pagamento.handlers;

import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;

public interface PagamentoOutHandler {
    void setarProximo(PagamentoOutHandler handler);
    PagamentoDTO handle(PagamentoDTO pagamento) throws Exception;
}
