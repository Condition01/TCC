package br.com.ifeira.compra.shared.dao;

import br.com.ifeira.compra.shared.entity.Endereco;
import br.com.ifeira.compra.shared.entity.Feira;
import br.com.ifeira.compra.shared.entity.Produto;
import br.com.ifeira.compra.shared.entity.ProdutoFeira;
import br.com.ifeira.compra.shared.enums.TipoProduto;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdutoFeiraDAO implements PersistivelContextual<ProdutoFeira, String> {

    private JdbcTemplate jdbcTemplate;
    private Persistivel<Endereco, Integer> enderecoDAO;

    public ProdutoFeiraDAO(JdbcTemplate jdbcTemplate) {
        this.enderecoDAO = new EnderecoDAO(jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ProdutoFeira salvar(ProdutoFeira item, String contexto) {
        return null;
    }

    @Override
    public List<ProdutoFeira> listar(String contexto) {
        String sql = "SELECT \n" +
                "\tpf.COD_PRODUTO,\n" +
                "\tpf.PRECO,\n" +
                "\tpf.ATIVO,\n" +
                "\tp.NOME AS NOME_PRODUTO,\n" +
                "\tp.DESCRICAO,\n" +
                "\tpf.UNIDADE_MEDIDA,\n" +
                "\tp.TIPO_PRODUTO,\n" +
                "\tf.NOME AS NOME_FEIRA,\n" +
                "\tpf.CONTEXTO,\n" +
                "\tf.TELEFONE_CONTATO,\n" +
                "\tf.LATITUDE,\n" +
                "\tf.LONGITUDE,\n" +
                "\tf.ID_ENDERECO\n" +
                "FROM PRODUTO_FEIRA pf INNER JOIN PRODUTO p " +
                "ON pf.COD_PRODUTO = p.COD INNER JOIN FEIRA f " +
                "ON f.CONTEXTO = pf.CONTEXTO " +
                "WHERE pf.CONTEXTO = ?";

        return jdbcTemplate.query(sql,  ps -> {
            ps.setString(1, contexto);
        }, rs -> {
            List<ProdutoFeira> listProdFeira = new ArrayList<>();

            Map<String, Produto> produtoMap = new HashMap();
            Map<String, Feira> feiraMap = new HashMap<>();

            while(rs.next()) {
                Produto prod = produtoMap.get(rs.getString("COD_PRODUTO")) != null ? produtoMap.get(rs.getString("COD")) : new Produto();
                Feira feira = feiraMap.get(rs.getString("CONTEXTO")) != null ? feiraMap.get(rs.getString("CONTEXTO")) : new Feira();

                prod.setCodProduto(rs.getString("COD_PRODUTO"));
                prod.setDescricao(rs.getString("DESCRICAO"));
                prod.setNome(rs.getString("NOME_PRODUTO"));
                prod.setTipoProduto(TipoProduto.valueOf(rs.getString("TIPO_PRODUTO")));
                prod.setUnidadeMedida(rs.getString("UNIDADE_MEDIDA"));

                feira.setNome(rs.getString("NOME_FEIRA"));
                feira.setContext(rs.getString("CONTEXTO"));
                feira.setEndereco(enderecoDAO.buscar(rs.getInt("ID_ENDERECO")));
                feira.setLatitute(rs.getString("LATITUDE"));
                feira.setLongitude(rs.getString("LONGITUDE"));

                ProdutoFeira produtoFeira = new ProdutoFeira();
                produtoFeira.setProduto(prod);
                produtoFeira.setFeira(feira);
                produtoFeira.setPreco(rs.getDouble("PRECO"));
                produtoFeira.setAtivo(rs.getBoolean("ATIVO"));

                listProdFeira.add(produtoFeira);

                prod.setProdutosFeira(listProdFeira);
                feira.setProdutosFeira(listProdFeira);
            }

            return listProdFeira;
        });
    }

    @Override
    public ProdutoFeira buscar(String key, String contexto) {
        String sql = "SELECT \n" +
                "\tpf.COD_PRODUTO,\n" +
                "\tpf.CONTEXTO,\n" +
                "\tpf.PRECO,\n" +
                "\tpf.ATIVO,\n" +
                "\tp.NOME AS NOME_PRODUTO,\n" +
                "\tp.DESCRICAO,\n" +
                "\tpf.UNIDADE_MEDIDA,\n" +
                "\tp.TIPO_PRODUTO,\n" +
                "\tf.NOME AS NOME_FEIRA,\n" +
                "\tf.TELEFONE_CONTATO,\n" +
                "\tf.LATITUDE,\n" +
                "\tf.LONGITUDE,\n" +
                "\tf.ID_ENDERECO\n" +
                "FROM PRODUTO_FEIRA pf INNER JOIN PRODUTO p " +
                "ON pf.COD_PRODUTO = p.COD INNER JOIN FEIRA f " +
                "ON f.CONTEXTO = pf.CONTEXTO " +
                "WHERE pf.COD_PRODUTO = ? and pf.CONTEXTO = ?";

        return jdbcTemplate.query(sql, ps -> {
            ps.setString(1, key);
            ps.setString(2, contexto);
        }, new ResultSetExtractor<ProdutoFeira>() {
            @Override
            public ProdutoFeira extractData(ResultSet rs) throws SQLException, DataAccessException {
                ProdutoFeira produtoFeira = null;
                while(rs.next()) {
                    Produto prod =  new Produto();
                    Feira feira =  new Feira();

                    prod.setCodProduto(rs.getString("COD_PRODUTO"));
                    prod.setDescricao(rs.getString("DESCRICAO"));
                    prod.setNome(rs.getString("NOME_PRODUTO"));
                    prod.setTipoProduto(TipoProduto.valueOf(rs.getString("TIPO_PRODUTO")));
                    prod.setUnidadeMedida(rs.getString("UNIDADE_MEDIDA"));

                    feira.setNome(rs.getString("NOME_FEIRA"));
                    feira.setContext(rs.getString("CONTEXTO"));
                    feira.setEndereco(enderecoDAO.buscar(rs.getInt("ID_ENDERECO")));
                    feira.setLatitute(rs.getString("LATITUDE"));
                    feira.setLongitude(rs.getString("LONGITUDE"));

                    produtoFeira.setProduto(prod);
                    produtoFeira.setFeira(feira);
                    produtoFeira.setPreco(rs.getDouble("PRECO"));
                    produtoFeira.setAtivo(rs.getBoolean("ATIVO"));

                    List<ProdutoFeira> produtoFeiraList = new ArrayList<>();
                    produtoFeiraList.add(produtoFeira);

                    prod.setProdutosFeira(produtoFeiraList);
                    feira.setProdutosFeira(produtoFeiraList);
                }
                return produtoFeira;
            }
        });
    }

    @Override
    public List<ProdutoFeira> buscarMultiplos(String key, String contexto) {
        String sql = "SELECT \n" +
                "\tpf.COD_PRODUTO,\n" +
                "\tpf.PRECO,\n" +
                "\tpf.ATIVO,\n" +
                "\tp.NOME AS NOME_PRODUTO,\n" +
                "\tp.DESCRICAO,\n" +
                "\tpf.UNIDADE_MEDIDA,\n" +
                "\tp.TIPO_PRODUTO,\n" +
                "\tf.NOME AS NOME_FEIRA,\n" +
                "\tpf.CONTEXTO,\n" +
                "\tf.TELEFONE_CONTATO,\n" +
                "\tf.LATITUDE,\n" +
                "\tf.LONGITUDE,\n" +
                "\tf.ID_ENDERECO\n" +
                "FROM PRODUTO_FEIRA pf INNER JOIN PRODUTO p " +
                "ON pf.COD_PRODUTO = p.COD INNER JOIN FEIRA f " +
                "ON f.CONTEXTO = pf.CONTEXTO " +
                "WHERE p.NOME LIKE ? and pf.CONTEXTO = ?";

        return jdbcTemplate.query(sql, ps -> {
                    ps.setString(1, key);
                    ps.setString(2, contexto);
        },rs -> {
            List<ProdutoFeira> listProdFeira = new ArrayList<>();

            Map<String, Produto> produtoMap = new HashMap();
            Map<String, Feira> feiraMap = new HashMap<>();

            while(rs.next()) {
                Produto prod = produtoMap.get(rs.getString("COD_PRODUTO")) != null ? produtoMap.get(rs.getString("COD")) : new Produto();
                Feira feira = feiraMap.get(rs.getString("CONTEXTO")) != null ? feiraMap.get(rs.getString("CONTEXTO")) : new Feira();

                prod.setCodProduto(rs.getString("COD_PRODUTO"));
                prod.setDescricao(rs.getString("DESCRICAO"));
                prod.setNome(rs.getString("NOME_PRODUTO"));
                prod.setTipoProduto(TipoProduto.valueOf(rs.getString("TIPO_PRODUTO")));
                prod.setUnidadeMedida(rs.getString("UNIDADE_MEDIDA"));

                feira.setNome(rs.getString("NOME_FEIRA"));
                feira.setContext(rs.getString("CONTEXTO"));
                feira.setEndereco(enderecoDAO.buscar(rs.getInt("ID_ENDERECO")));
                feira.setLatitute(rs.getString("LATITUDE"));
                feira.setLongitude(rs.getString("LONGITUDE"));

                ProdutoFeira produtoFeira = new ProdutoFeira();
                produtoFeira.setProduto(prod);
                produtoFeira.setFeira(feira);
                produtoFeira.setPreco(rs.getDouble("PRECO"));
                produtoFeira.setAtivo(rs.getBoolean("ATIVO"));

                listProdFeira.add(produtoFeira);

                prod.setProdutosFeira(listProdFeira);
                feira.setProdutosFeira(listProdFeira);
            }

            return listProdFeira;
        });
    }

    @Override
    public ProdutoFeira editar(ProdutoFeira obj) {
        return null;
    }

}
