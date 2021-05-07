package br.com.ifeira.pagamento.exceptions;

import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;

public class PagamentoInvalidoException extends Exception {

    private PagamentoDTO pagamentoDTO;

    public PagamentoInvalidoException(PagamentoDTO pagamentoDTO) {
        super();
        this.pagamentoDTO = pagamentoDTO;
    }

    @Override
    public String getMessage() {
        return "Pagamento" + pagamentoDTO.getIdPagamento() + " Inválido";
    }

}
