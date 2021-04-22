package br.com.ifeira.pagamento.handlers;

import br.com.ifeira.pagamento.entity.PagamentoResponse;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;

public class HandlerPagamento extends PagamentoOutBaseHandler {
    @Override
    public PagamentoResponse handle(PagamentoDTO pagamento) throws Exception  {
        System.out.println(pagamento.toString());
        return null;
    }
}
