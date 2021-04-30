package br.com.ifeira.compra.shared.dao;

import br.com.ifeira.compra.shared.entity.Endereco;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EnderecoDAO implements Persistivel<Endereco, Integer>{

    private JdbcTemplate jdbcTemplate;

    public EnderecoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Endereco salvar(Endereco item) {
        return null;
    }

    @Override
    public List<Endereco> listar() {
        return null;
    }

    @Override
    public Endereco buscar(Integer key) {
        String sql = "SELECT * FROM ENDERECO e WHERE e.ID = ?";

        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, key);
            }
        },
        new ResultSetExtractor<Endereco>() {
            @Override
            public Endereco extractData(ResultSet rs) throws SQLException, DataAccessException {
                Endereco endereco = null;
                while(rs.next()) {
                    if(endereco == null) {
                        endereco = new Endereco();
                        endereco.setBairro(rs.getString("BAIRRO"));
                        endereco.setCidade(rs.getString("CIDADE"));
                        endereco.setUF(rs.getString("UF"));
                        endereco.setComplemento(rs.getString("COMPLEMENTO"));
                        endereco.setCep(rs.getString("CEP"));
                        endereco.setNumero(rs.getString("NUMERO"));
                        endereco.setLogradouro(rs.getString("LOGRADOURO"));
                    }
                }
                return endereco;
            }
        });
    }

    @Override
    public List<Endereco> buscarMultiplos(Integer key) {
        return null;
    }

    @Override
    public Endereco editar(Endereco item) {
        return null;
    }
}
