package br.com.ifeira.pagamento.handlers;

import br.com.ifeira.pagamento.dao.PagamentoDAO;
import br.com.ifeira.pagamento.entity.PagamentoResponse;
import br.com.ifeira.pagamento.exceptions.PagamentoInvalidoException;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import org.springframework.jdbc.core.JdbcTemplate;

public class HandlerValidacao extends PagamentoOutBaseHandler {

    private PagamentoDAO pagamentoDAO;

    public HandlerValidacao(JdbcTemplate jdbcTemplate) {
        //TODO - PROGRAMAR PERSISTÊNCIA NAS CAMADAS DE DADOS
        this.pagamentoDAO = new PagamentoDAO(jdbcTemplate);
    }

    public boolean validaDadosPag(PagamentoDTO pagamento) {
        return (pagamento.getNumeroCartao() != null
                && pagamento.getCvv() != null && pagamento.getValidadeCartao() != null && pagamento.getNomeCartao() != null);
    }

    public boolean validaCancelamento(PagamentoDTO pagamentoDTO) {
        return this.pagamentoDAO.verificarCancelamento(pagamentoDTO);
    }

    @Override
    public PagamentoResponse handle(PagamentoDTO pagamento) throws Exception {
        if (pagamento.getTentativasMQ() < 3 && validaDadosPag(pagamento) & validaCancelamento(pagamento)) {
            pagamento.setTentativasMQ(pagamento.getTentativasMQ() + 1);
        } else {
            throw new PagamentoInvalidoException(pagamento);
        }

        if(this.getNext() != null) {
            return this.getNext().handle(pagamento);
        }
        return null;
    }

}
