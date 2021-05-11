package br.com.ifeira.entrega.dao;

import br.com.ifeira.compra.shared.dao.FeiraDAO;
import br.com.ifeira.compra.shared.dao.Persistivel;
import br.com.ifeira.compra.shared.dao.PessoaDAO;
import br.com.ifeira.compra.shared.entity.Feira;
import br.com.ifeira.compra.shared.entity.Pessoa;
import br.com.ifeira.entrega.entity.Entregador;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class EntregadorDAO implements Persistivel<Entregador, String> {

    private JdbcTemplate jdbcTemplate;
    private Persistivel<Pessoa, String> pessoaDAO;
    private Persistivel<Feira, String> feiraDAO;

    public EntregadorDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.pessoaDAO = new PessoaDAO(jdbcTemplate);
        this.feiraDAO = new FeiraDAO(jdbcTemplate);
    }

    @Override
    public Entregador salvar(Entregador item) {
        return null;
    }

    @Override
    public List<Entregador> listar() {
        return null;
    }

    @Override
    public Entregador buscar(String identificador) {
        String sql = "SELECT\n" +
                "\tef.CONTEXTO,\n" +
                "\tef.CPF_ENTREGADOR,\n" +
                "\tp.EMAIL\n" +
                "FROM\n" +
                "\tENTREGADOR_FEIRA ef\n" +
                "INNER JOIN PESSOA p ON\n" +
                "\tef.CPF_ENTREGADOR = p.CPF \n" +
                "WHERE p.EMAIL = ?";

        return jdbcTemplate.query(sql, ps -> {
            ps.setString(1, identificador);
        }, rs -> {
            Entregador entregador = null;
            List<Feira> feiras = new ArrayList<>();
            while (rs.next()) {
                if (entregador != null) {
                    Feira feira = this.feiraDAO.buscar(rs.getString("CONTEXTO"));
                    feiras.add(feira);
                } else {
                    Pessoa pessoa = this.pessoaDAO.buscar(identificador);
                    entregador = Entregador.pessoaToEntregador(pessoa);
                    entregador.setFeiras(feiras);
                    Feira feira = this.feiraDAO.buscar(rs.getString("CONTEXTO"));
                    feiras.add(feira);
                }
            }
            return entregador;
        });
    }

    @Override
    public List<Entregador> buscarMultiplos(String key) {
        return null;
    }

    @Override
    public Entregador editar(Entregador item) {
        return null;
    }

    @Override
    public List<Entregador> buscarComParametros(Object[] params) {
        String contexto = (String) params[0];

        String sql = "SELECT\n" +
                "\tef.CONTEXTO,\n" +
                "\tp.EMAIL\n" +
                "FROM\n" +
                "\tENTREGADOR_FEIRA ef\n" +
                "INNER JOIN PESSOA p ON\n" +
                "\tp.CPF = ef.CPF_ENTREGADOR\n" +
                "WHERE\n" +
                "\tCONTEXTO = ?";

        return jdbcTemplate.query(sql, ps -> {
            ps.setString(1, contexto);
        }, rs -> {
            List<Entregador> entregadores = new ArrayList<>();
            while (rs.next()) {
                Entregador entregador = buscar(rs.getString("EMAIL"));
                entregadores.add(entregador);
            }
            return entregadores;
        });
    }

}
