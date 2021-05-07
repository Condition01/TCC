package br.com.ifeira.compra.shared.dao;

import br.com.ifeira.compra.shared.entity.Endereco;
import br.com.ifeira.compra.shared.entity.Feira;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class FeiraDAO implements Persistivel<Feira, String> {

    private JdbcTemplate jdbcTemplate;
    private Persistivel<Endereco, Integer> enderecoDAO;

    public FeiraDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.enderecoDAO = new EnderecoDAO(jdbcTemplate);
    }

    @Override
    public Feira salvar(Feira item) {
        return null;
    }

    @Override
    public List<Feira> listar() {
        String sql = "SELECT * FROM FEIRA";

        return jdbcTemplate.query(sql, rs -> {
            List<Feira> feiras = new ArrayList<>();

            while(rs.next()) {
                Feira feira = new Feira();

                feira.setDiaEntrega(rs.getInt("DIA_ENTREGA"));
                feira.setNome(rs.getString("NOME"));
                feira.setContext(rs.getString("CONTEXTO"));
                feira.setEndereco(enderecoDAO.buscar(rs.getInt("ID_ENDERECO")));
                feira.setLatitute(rs.getString("LATITUDE"));
                feira.setLongitude(rs.getString("LONGITUDE"));

                feiras.add(feira);
            }

            return feiras;
        });

    }

    @Override
    public Feira buscar(String key) {
        String sql = "SELECT * FROM FEIRA f WHERE f.CONTEXTO = ?";

        return jdbcTemplate.query(sql, ps -> {
            ps.setString(1, key);
        },rs -> {
            Feira feira = null;

            while(rs.next()) {
                if(feira == null) {
                    feira = new Feira();
                    feira.setDiaEntrega(rs.getInt("DIA_ENTREGA"));
                    feira.setNome(rs.getString("NOME"));
                    feira.setContext(rs.getString("CONTEXTO"));
                    feira.setEndereco(enderecoDAO.buscar(rs.getInt("ID_ENDERECO")));
                    feira.setLatitute(rs.getString("LATITUDE"));
                    feira.setLongitude(rs.getString("LONGITUDE"));
                }
            }

            return feira;
        });
    }

    @Override
    public List<Feira> buscarMultiplos(String key) {
        return null;
    }

    @Override
    public Feira editar(Feira item) {
        return null;
    }

    @Override
    public List<Feira> buscarComParametros(Object[] params) {
        return null;
    }

}
