package br.com.ifeira.compra.shared.dao;

import br.com.ifeira.compra.shared.entity.Endereco;
import br.com.ifeira.compra.shared.entity.Feira;
import br.com.ifeira.compra.shared.entity.Produto;
import br.com.ifeira.compra.shared.entity.ProdutoFeira;
import br.com.ifeira.compra.shared.enums.TipoProduto;
import javafx.util.Pair;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdutoFeiraDAO implements Persistivel<ProdutoFeira, Pair<String, Integer>> {

    private JdbcTemplate jdbcTemplate;
    private Persistivel<Endereco, Integer> enderecoDAO;

    public ProdutoFeiraDAO(JdbcTemplate jdbcTemplate) {
        this.enderecoDAO = new EnderecoDAO(jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ProdutoFeira salvar(ProdutoFeira item) {
        return null;
    }

    @Override
    public List<ProdutoFeira> listar() {
        String sql = "SELECT \n" +
                "\tpf.COD_PRODUTO,\n" +
                "\tpf.ID_FEIRA,\n" +
                "\tpf.PRECO,\n" +
                "\tpf.ATIVO,\n" +
                "\tp.NOME AS NOME_PRODUTO,\n" +
                "\tp.DESCRICAO,\n" +
                "\tp.TIPO_PRODUTO,\n" +
                "\tf.NOME AS NOME_FEIRA,\n" +
                "\tf.CONTEXTO,\n" +
                "\tf.TELEFONE_CONTATO,\n" +
                "\tf.LATITUDE,\n" +
                "\tf.LONGITUDE,\n" +
                "\tf.ID_ENDERECO\n" +
                "FROM PRODUTO_FEIRA pf INNER JOIN PRODUTO p ON pf.COD_PRODUTO = p.COD INNER JOIN FEIRA f ON f.ID = pf.ID_FEIRA";

        return jdbcTemplate.query(sql, new ResultSetExtractor<List<ProdutoFeira>>() {
            @Override
            public List<ProdutoFeira> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<ProdutoFeira> listProdFeira = new ArrayList<>();

                Map<String, Produto> produtoMap = new HashMap();
                Map<Integer, Feira> feiraMap = new HashMap<>();

                while(rs.next()) {
                    Produto prod = produtoMap.get(rs.getString("COD_PRODUTO")) != null ? produtoMap.get(rs.getString("COD")) : new Produto();
                    Feira feira = feiraMap.get(rs.getString("ID_FEIRA")) != null ? feiraMap.get(rs.getString("ID_FEIRA")) : new Feira();

                    prod.setCodProduto(rs.getString("COD_PRODUTO"));
                    prod.setDescricao(rs.getString("DESCRICAO"));
                    prod.setNome(rs.getString("NOME_PRODUTO"));
                    prod.setTipoProduto(TipoProduto.valueOf(rs.getString("TIPO_PRODUTO")));

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
            }
        });
    }

    @Override
    public ProdutoFeira buscar(Pair<String, Integer> key) {
        return null;
    }

    @Override
    public ProdutoFeira editar(ProdutoFeira obj) {
        return null;
    }

}
