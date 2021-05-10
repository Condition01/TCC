package br.com.ifeira.entrega.dao;

import br.com.ifeira.compra.shared.dao.Persistivel;
import br.com.ifeira.entrega.entity.Entrega;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class EntregaDAO implements Persistivel<Entrega, Long> {

    private JdbcTemplate jdbcTemplate;

    public EntregaDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Entrega salvar(Entrega item) {

//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        String sql
//                = "INSERT INTO PEDIDO\n" +
//                "(DATA_PEDIDO, STATUS_PEDIDO, DATA_ENTREGA, CUPOM, CPF_PESSOA, COBRANCA, VALOR_TOTAL, VALOR_ORIGINAL)\n" +
//                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
//
//        jdbcTemplate.update(connection -> {
//            PreparedStatement ps = connection
//                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            ps.setDate(1, new Date(item.getData().getTime()));
//            ps.setString(2, item.getStatusPedido().name());
//            ps.setDate(3, new Date(item.getDataEntrega().getTime()));
//            ps.setString(4, item.getCupom().getNome());
//            ps.setString(5, item.getCliente().getCpf());
//            ps.setString(6, item.getCobranca());
//            ps.setDouble(7, item.getValorTotal());
//            ps.setDouble(8, item.getValorOriginal());
//            return ps;
//        }, keyHolder);
//
//        Long primaryKey = keyHolder.getKey().longValue();
//        item.setNumeroPedido(primaryKey);
//        return item;
        return null;
    }

    @Override
    public List<Entrega> listar() {
        return null;
    }

    @Override
    public Entrega buscar(Long identificador) {
//        String sql = "SELECT\n" +
//                "\tpd.NUMERO,\n" +
//                "\tpd.DATA_PEDIDO,\n" +
//                "\tpd.STATUS_PEDIDO,\n" +
//                "\tpd.DATA_ENTREGA,\n" +
//                "\tpd.CUPOM,\n" +
//                "\tpd.CPF_PESSOA,\n" +
//                "\tpd.COBRANCA,\n" +
//                "\tpd.VALOR_TOTAL,\n" +
//                "\tpd.VALOR_ORIGINAL,\n" +
//                "\tc.COD_PRODUTO,\n" +
//                "\tc.CONTEXTO,\n" +
//                "\tc.QUANTIDADE,\n" +
//                "\tp.EMAIL\n" +
//                "FROM\n" +
//                "\tPEDIDO pd\n" +
//                "INNER JOIN PESSOA p ON\n" +
//                "\tp.CPF = pd.CPF_PESSOA\n" +
//                "INNER JOIN CARRINHO c ON\n" +
//                "\tc.NUMERO_PEDIDO = pd.NUMERO\n" +
//                "WHERE\n" +
//                "\tpd.NUMERO = ?";
//
//        return jdbcTemplate.query(sql, ps -> {
//            ps.setLong(1, key);
//        }, rs -> {
//            Pedido pedido = null;
//
//            while (rs.next()) {
//                if(pedido == null) {
//                    pedido = new Pedido();
//
//                    Carrinho carrinho = new Carrinho();
//
//                    pedido.setCarrinho(carrinho);
//                    pedido.setNumeroPedido(rs.getLong("NUMERO"));
//                    pedido.setData(rs.getDate("DATA_PEDIDO"));
//                    pedido.setStatusPedido(StatusPedido.valueOf(rs.getString("STATUS_PEDIDO")));
//                    pedido.setDataEntrega(rs.getDate("DATA_ENTREGA"));
//                    pedido.setCupom(this.cupomDAO.buscar(rs.getString("CUPOM")));
//                    pedido.setCliente(this.pessoaDAO.buscar(rs.getString("EMAIL")));
//                    pedido.setCobranca(rs.getString("COBRANCA"));
//                    pedido.setValorTotal(rs.getDouble("VALOR_TOTAL"));
//                    pedido.setValorOriginal(rs.getDouble("VALOR_ORIGINAL"));
//
//                    ProdutoFeira produtoFeira = this.produtoFeiraDAO.buscar(
//                            rs.getString("COD_PRODUTO"),
//                            rs.getString("CONTEXTO"));
//
//                    List<ProdutoQuantidade> prodQtdList = new ArrayList<>();
//                    prodQtdList.add(new ProdutoQuantidade(produtoFeira, rs.getInt("QUANTIDADE")));
//
//                    carrinho.setListaProdutoQuantidade(prodQtdList);
//                } else {
//                    List<ProdutoQuantidade> prodQtdList = pedido.getCarrinho().getListaProdutoQuantidade();
//
//                    ProdutoFeira produtoFeira = this.produtoFeiraDAO.buscar(
//                            rs.getString("COD_PRODUTO"),
//                            rs.getString("CONTEXTO"));
//
//                    prodQtdList.add(new ProdutoQuantidade(produtoFeira, rs.getInt("QUANTIDADE")));
//                }
//            }
//
//            return pedido;
//        });
        return null;
    }

    @Override
    public List<Entrega> buscarMultiplos(Long key) {
        return null;
    }

    @Override
    public Entrega editar(Entrega item) {
        return null;
    }

    @Override
    public List<Entrega> buscarComParametros(Object[] params) {
        return null;
    }
}
