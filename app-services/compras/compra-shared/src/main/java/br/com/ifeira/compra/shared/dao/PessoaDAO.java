package br.com.ifeira.compra.shared.dao;

import br.com.ifeira.compra.shared.entity.Endereco;
import br.com.ifeira.compra.shared.entity.Pessoa;
import br.com.ifeira.compra.shared.enums.Role;
import br.com.ifeira.compra.shared.enums.Sexo;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PessoaDAO implements Persistivel<Pessoa, String> {

    private JdbcTemplate jdbcTemplate;
    private Persistivel<Endereco, Integer> enderecoDAO;

    public PessoaDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.enderecoDAO = new EnderecoDAO(jdbcTemplate);
    }

    @Override
    public Pessoa salvar(Pessoa item) {
        return null;
    }

    @Override
    public List<Pessoa> listar() {
        return null;
    }

    @Override
    public Pessoa buscar(String key) {
        String sql = "SELECT * FROM PESSOA WHERE EMAIL = ?";

        return jdbcTemplate.query(sql, ps -> {
            ps.setString(1, key);
        }, rs -> {
            Pessoa pessoa = null;

            while (rs.next()) {
                if (pessoa == null) {
                    pessoa = new Pessoa();
                    pessoa.setCpf(rs.getString("CPF"));
                    pessoa.setNome(rs.getString("NOME"));
                    pessoa.setEndereco(this.enderecoDAO.buscar(rs.getInt("ID_ENDERECO")));
                    pessoa.setRole(Role.fromNumber(rs.getString("ROLE")));
                    pessoa.setSexo(Sexo.valueOf(rs.getString("SEXO")));
                    pessoa.setDataNascimento(rs.getDate("DATA_NASC"));
                    pessoa.setTelefone(rs.getString("TELEFONE"));
                    pessoa.setSobrenome(rs.getString("SOBRENOME"));
                    pessoa.setSituacao(rs.getString("SITUACAO"));
                    pessoa.setEmail(rs.getString("EMAIL"));
                }
            }

            return pessoa;
        });
    }

    @Override
    public List<Pessoa> buscarMultiplos(String key) {
        return null;
    }

    @Override
    public Pessoa editar(Pessoa obj) {
        return null;
    }

    @Override
    public List<Pessoa> buscarComParametros(Object[] params) {
        return null;
    }

}
