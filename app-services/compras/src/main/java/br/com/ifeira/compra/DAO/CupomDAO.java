package br.com.ifeira.compra.dao;

import br.com.ifeira.compra.shared.dao.Persistivel;
import br.com.ifeira.compra.shared.entity.Cupom;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CupomDAO implements Persistivel<Cupom, String> {

    private JdbcTemplate jdbcTemplate;

    public CupomDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Cupom salvar(Cupom item) {
        return null;
    }

    @Override
    public List<Cupom> listar() {
        return null;
    }

    @Override
    public Cupom buscar(String identificador) {
        String sql = "SELECT * FROM CUPOM WHERE NOME = ?";

        return jdbcTemplate.query(sql, ps -> {
            ps.setString(1, identificador);
        }, rs -> {
            Cupom cupom = null;

            while (rs.next()) {
                if (cupom == null) {
                    cupom = new Cupom();
                    cupom.setNome(rs.getString("NOME"));
                    cupom.setPorcentagem(rs.getDouble("PORCENTAGEM"));
                    cupom.setAtivo(rs.getBoolean("ATIVO"));
                }
            }

            return cupom;
        });
    }

    @Override
    public List<Cupom> buscarMultiplos(String key) {
        return null;
    }

    @Override
    public Cupom editar(Cupom item) {
        return null;
    }
}
