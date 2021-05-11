package br.com.ifeira.compra.shared.dao;

import br.com.ifeira.compra.shared.entity.*;
import br.com.ifeira.compra.shared.enums.StatusPedido;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PedidoDAO implements Persistivel<Pedido, Long> {

    private JdbcTemplate jdbcTemplate;
    private PersistivelContextual<ProdutoFeira, String> produtoFeiraDAO;
    private Persistivel<Cupom, String> cupomDAO;
    private Persistivel<Pessoa, String> pessoaDAO;

    public PedidoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.produtoFeiraDAO = new ProdutoFeiraDAO(jdbcTemplate);
        this.cupomDAO = new CupomDAO(jdbcTemplate);
        this.pessoaDAO = new PessoaDAO(jdbcTemplate);
    }

    @Override
    public Pedido salvar(Pedido item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql
                = "INSERT INTO PEDIDO\n" +
                "(DATA_PEDIDO, STATUS_PEDIDO, DATA_ENTREGA, CUPOM, CPF_PESSOA, COBRANCA, VALOR_TOTAL, VALOR_ORIGINAL)\n" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, new Date(item.getData().getTime()));
            ps.setString(2, item.getStatusPedido().name());
            ps.setDate(3, new Date(item.getDataEntrega().getTime()));
            ps.setString(4, item.getCupom().getNome());
            ps.setString(5, item.getCliente().getCpf());
            ps.setString(6, item.getCobranca());
            ps.setDouble(7, item.getValorTotal());
            ps.setDouble(8, item.getValorOriginal());
            return ps;
        }, keyHolder);

        Long primaryKey = keyHolder.getKey().longValue();
        item.setNumeroPedido(primaryKey);
        return item;
    }

    @Override
    public List<Pedido> listar() {
        return null;
    }

    @Override
    public Pedido buscar(Long key) {

        String sql = "SELECT\n" +
                "\tpd.NUMERO,\n" +
                "\tpd.DATA_PEDIDO,\n" +
                "\tpd.STATUS_PEDIDO,\n" +
                "\tpd.DATA_ENTREGA,\n" +
                "\tpd.CUPOM,\n" +
                "\tpd.CPF_PESSOA,\n" +
                "\tpd.COBRANCA,\n" +
                "\tpd.VALOR_TOTAL,\n" +
                "\tpd.VALOR_ORIGINAL,\n" +
                "\tc.COD_PRODUTO,\n" +
                "\tc.CONTEXTO,\n" +
                "\tc.QUANTIDADE,\n" +
                "\tp.EMAIL\n" +
                "FROM\n" +
                "\tPEDIDO pd\n" +
                "INNER JOIN PESSOA p ON\n" +
                "\tp.CPF = pd.CPF_PESSOA\n" +
                "INNER JOIN CARRINHO c ON\n" +
                "\tc.NUMERO_PEDIDO = pd.NUMERO\n" +
                "WHERE\n" +
                "\tpd.NUMERO = ?";

        return jdbcTemplate.query(sql, ps -> {
            ps.setLong(1, key);
        }, rs -> {
            Pedido pedido = null;

            while (rs.next()) {
                if(pedido == null) {
                    pedido = new Pedido();

                    Carrinho carrinho = new Carrinho();

                    pedido.setCarrinho(carrinho);
                    pedido.setNumeroPedido(rs.getLong("NUMERO"));
                    pedido.setData(rs.getDate("DATA_PEDIDO"));
                    pedido.setStatusPedido(StatusPedido.valueOf(rs.getString("STATUS_PEDIDO")));
                    pedido.setDataEntrega(rs.getDate("DATA_ENTREGA"));
                    pedido.setCupom(this.cupomDAO.buscar(rs.getString("CUPOM")));
                    pedido.setCliente(this.pessoaDAO.buscar(rs.getString("EMAIL")));
                    pedido.setCobranca(rs.getString("COBRANCA"));
                    pedido.setValorTotal(rs.getDouble("VALOR_TOTAL"));
                    pedido.setValorOriginal(rs.getDouble("VALOR_ORIGINAL"));

                    ProdutoFeira produtoFeira = this.produtoFeiraDAO.buscar(
                            rs.getString("COD_PRODUTO"),
                            rs.getString("CONTEXTO"));

                    List<ProdutoQuantidade> prodQtdList = new ArrayList<>();
                    prodQtdList.add(new ProdutoQuantidade(produtoFeira, rs.getInt("QUANTIDADE")));

                    carrinho.setListaProdutoQuantidade(prodQtdList);
                } else {
                    List<ProdutoQuantidade> prodQtdList = pedido.getCarrinho().getListaProdutoQuantidade();

                    ProdutoFeira produtoFeira = this.produtoFeiraDAO.buscar(
                            rs.getString("COD_PRODUTO"),
                            rs.getString("CONTEXTO"));

                    prodQtdList.add(new ProdutoQuantidade(produtoFeira, rs.getInt("QUANTIDADE")));
                }
            }

            return pedido;
        });

    }


    @Override
    public List<Pedido> buscarMultiplos(Long key) {
        return null;
    }

    @Override
    public Pedido editar(Pedido item) {
        String sql = "UPDATE PEDIDO SET STATUS_PEDIDO = ?, DATA_ENTREGA = ? WHERE NUMERO = ?";

        this.jdbcTemplate.update(sql, item.getStatusPedido().name(), item.getDataEntrega(), item.getNumeroPedido());

        return item;
    }

    @Override
    public List<Pedido> buscarComParametros(Object[] params) {
        Pessoa pessoa = (Pessoa) params[0];

        String sql = "SELECT\n" +
                "\tpd.NUMERO,\n" +
                "\tpd.DATA_PEDIDO,\n" +
                "\tpd.STATUS_PEDIDO,\n" +
                "\tpd.DATA_ENTREGA,\n" +
                "\tpd.CUPOM,\n" +
                "\tpd.CPF_PESSOA,\n" +
                "\tpd.COBRANCA,\n" +
                "\tpd.VALOR_TOTAL,\n" +
                "\tpd.VALOR_ORIGINAL,\n" +
                "\tc.COD_PRODUTO,\n" +
                "\tc.CONTEXTO,\n" +
                "\tc.QUANTIDADE\n" +
                "FROM\n" +
                "\tPEDIDO pd\n" +
                "INNER JOIN PESSOA p ON\n" +
                "\tp.CPF = pd.CPF_PESSOA\n" +
                "INNER JOIN CARRINHO c ON\n" +
                "\tc.NUMERO_PEDIDO = pd.NUMERO\n" +
                "WHERE\n" +
                "\tpd.CPF_PESSOA = ?";

        return jdbcTemplate.query(sql, ps -> {
            ps.setString(1, pessoa.getCpf());
        }, rs -> {
            Map<Long, Pedido> pedidos = new HashMap<>();

            while (rs.next()) {
                Long numeroPedido = rs.getLong("NUMERO");

                if (pedidos.get(numeroPedido) != null) {
                    Pedido pedido = pedidos.get(numeroPedido);

                    List<ProdutoQuantidade> prodQtdList = pedido.getCarrinho().getListaProdutoQuantidade();

                    ProdutoFeira produtoFeira = this.produtoFeiraDAO.buscar(
                            rs.getString("COD_PRODUTO"),
                            rs.getString("CONTEXTO"));

                    prodQtdList.add(new ProdutoQuantidade(produtoFeira, rs.getInt("QUANTIDADE")));
                } else {
                    Pedido pedido = new Pedido();
                    Carrinho carrinho = new Carrinho();

                    pedido.setCarrinho(carrinho);
                    pedido.setNumeroPedido(rs.getLong("NUMERO"));
                    pedido.setData(rs.getDate("DATA_PEDIDO"));
                    pedido.setStatusPedido(StatusPedido.valueOf(rs.getString("STATUS_PEDIDO")));
                    pedido.setDataEntrega(rs.getDate("DATA_ENTREGA"));
                    pedido.setCupom(this.cupomDAO.buscar(rs.getString("CUPOM")));
                    pedido.setCliente(pessoa);
                    pedido.setCobranca(rs.getString("COBRANCA"));
                    pedido.setValorTotal(rs.getDouble("VALOR_TOTAL"));
                    pedido.setValorOriginal(rs.getDouble("VALOR_ORIGINAL"));

                    ProdutoFeira produtoFeira = this.produtoFeiraDAO.buscar(
                            rs.getString("COD_PRODUTO"),
                            rs.getString("CONTEXTO"));

                    List<ProdutoQuantidade> prodQtdList = new ArrayList<>();
                    prodQtdList.add(new ProdutoQuantidade(produtoFeira, rs.getInt("QUANTIDADE")));

                    carrinho.setListaProdutoQuantidade(prodQtdList);

                    pedidos.put(numeroPedido, pedido);
                }
            }

            return new ArrayList<>(pedidos.values());
        });

    }

}
