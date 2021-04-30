package br.com.ifeira.pagamento.dao;

import br.com.ifeira.compra.shared.dao.Persistivel;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PagamentoDAO implements Persistivel<PagamentoDTO, Integer> {

    private JdbcTemplate jdbcTemplate;

    public PagamentoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public PagamentoDTO salvar(PagamentoDTO item) {
        return null;
    }

    @Override
    public List<PagamentoDTO> listar() {
        return null;
    }

    @Override
    public PagamentoDTO buscar(Integer key) {
        return null;
    }

    @Override
    public List<PagamentoDTO> buscarMultiplos(Integer key) {
        return null;
    }

    @Override
    public PagamentoDTO editar(PagamentoDTO item) {
        return null;
    }
}
