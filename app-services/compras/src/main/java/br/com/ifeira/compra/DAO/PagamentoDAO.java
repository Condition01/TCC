package br.com.ifeira.compra.dao;

import br.com.ifeira.compra.entity.Pagamento;
import br.com.ifeira.compra.shared.dao.Persistivel;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

public class PagamentoDAO implements Persistivel<Pagamento, Integer> {

    private JdbcTemplate jdbcTemplate;

    public PagamentoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Pagamento salvar(Pagamento item) {
        return null;
    }

    @Override
    public List<Pagamento> listar() {
        return null;
    }

    @Override
    public Pagamento buscar(Integer key) {
        return null;
    }

    @Override
    public Pagamento editar(Pagamento item) {
        return null;
    }

}