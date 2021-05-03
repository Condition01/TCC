package br.com.ifeira.compra.handlers;

import br.com.ifeira.compra.entity.Pagamento;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;

public interface PagamentoInHandler {
    void setNext(PagamentoInHandler pagamentoInHandler);
    PagamentoDTO handle(Pagamento pagamento) throws Exception;
}
