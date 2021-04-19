package br.com.ifeira.pagamento.handlers;

import br.com.ifeira.pagamento.entity.PagamentoResponse;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;

public interface PagamentoOutHandler {
    void setNext(PagamentoOutHandler handler);
    PagamentoResponse handle(PagamentoDTO pagamento);
}
