package br.com.ifeira.compra.dao;

import br.com.ifeira.compra.entity.Pagamento;
import br.com.ifeira.compra.shared.dao.PedidoDAO;
import br.com.ifeira.compra.shared.dao.Persistivel;
import br.com.ifeira.compra.shared.entity.Pedido;
import br.com.ifeira.compra.shared.entity.Pessoa;
import br.com.ifeira.compra.shared.enums.StatusPagamento;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.nio.charset.StandardCharsets;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO implements Persistivel<Pagamento, Long> {

    private JdbcTemplate jdbcTemplate;
    private Persistivel<Pedido, Long> pedidoDAO;

    public PagamentoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.pedidoDAO = new PedidoDAO(jdbcTemplate);
    }

    @Override
    public Pagamento salvar(Pagamento item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql
                = "INSERT INTO PAGAMENTO\n" +
                "(NUMERO_PEDIDO, NOME_CARTAO, NUMERO_CARTAO, VALIDADE_CARTAO, CVV, DATA_PAGAMENTO, STATUS_PAGAMENTO, SALVA_DADOS_PAG)\n" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        List<SqlParameter> declaredParams = new ArrayList<>();

        declaredParams.add(new SqlParameter(Types.BIGINT));
        declaredParams.add(new SqlParameter(Types.VARCHAR));
        declaredParams.add(new SqlParameter(Types.BLOB));
        declaredParams.add(new SqlParameter(Types.BLOB));
        declaredParams.add(new SqlParameter(Types.BLOB));
        declaredParams.add(new SqlParameter(Types.DATE));
        declaredParams.add(new SqlParameter(Types.VARCHAR));
        declaredParams.add(new SqlParameter(Types.BOOLEAN));

        Object[] parametros = item.getSalvaDadosPag() ? gerarParamComDadosPag(item) : gerarParamSemDadosPag(item);

        PreparedStatementCreatorFactory pscFactory =
                new PreparedStatementCreatorFactory(sql, declaredParams);

        pscFactory.setReturnGeneratedKeys(true);

        this.jdbcTemplate.update(
                pscFactory.newPreparedStatementCreator(
                        parametros), keyHolder);

        Long primaryKey = keyHolder.getKey().longValue();
        item.setId(primaryKey);
        return item;
    }

    public Object[] gerarParamComDadosPag(Pagamento item) {
        byte[] numeroCartaoByte = item.getNumeroCartao().getBytes(StandardCharsets.ISO_8859_1);
        byte[] validadeCartaoByte = item.getValidadeCartao().getBytes(StandardCharsets.ISO_8859_1);
        byte[] cvvByte = item.getCvv().getBytes(StandardCharsets.ISO_8859_1);

        Object[] parametros = new Object[8];

        parametros[0] = item.getPedido().getNumeroPedido();
        parametros[1] = item.getNomeCartao();
        parametros[2] = numeroCartaoByte;
        parametros[3] = validadeCartaoByte;
        parametros[4] = cvvByte;
        parametros[5] = item.getData();
        parametros[6] = item.getStatusPagamento().name();
        parametros[7] = item.getSalvaDadosPag();

        return parametros;
    }

    public Object[] gerarParamSemDadosPag(Pagamento item) {
        Object[] parametros = new Object[8];

        parametros[0] = item.getPedido().getNumeroPedido();
        parametros[1] = null;
        parametros[2] = null;
        parametros[3] = null;
        parametros[4] = null;
        parametros[5] = item.getData();
        parametros[6] = item.getStatusPagamento().name();
        parametros[7] = item.getSalvaDadosPag();

        return parametros;
    }

    @Override
    public List<Pagamento> listar() {
        return null;
    }

    @Override
    public Pagamento buscar(Long key) {
        return null;
    }

    @Override
    public List<Pagamento> buscarMultiplos(Long key) {
        return null;
    }

    @Override
    public Pagamento editar(Pagamento item) {
        return null;
    }

    @Override
    public List<Pagamento> buscarComParametros(Object[] params) {
        Pessoa pessoa = (Pessoa) params[0];

        String sql = "SELECT\n" +
                "\tpa.ID,\n" +
                "\tpa.NUMERO_PEDIDO,\n" +
                "\tpa.STATUS_PAGAMENTO,\n" +
                "\tpa.DATA_PAGAMENTO\n" +
                "FROM\n" +
                "\tPAGAMENTO pa\n" +
                "INNER JOIN PEDIDO p ON\n" +
                "\tpa.NUMERO_PEDIDO = p.NUMERO\n" +
                "WHERE\n" +
                "\tp.CPF_PESSOA = ?";

        return jdbcTemplate.query(sql, ps -> {
            ps.setString(1, pessoa.getCpf());
        }, rs -> {

            List<Pagamento> pagamentos = new ArrayList<>();

            while (rs.next()) {
                Pagamento pagamento = new Pagamento();

                pagamento.setId(rs.getLong("ID"));
                pagamento.setData(rs.getDate("DATA_PAGAMENTO"));
                pagamento.setStatusPagamento(StatusPagamento.valueOf(rs.getString("STATUS_PAGAMENTO")));
                pagamento.setPedido(this.pedidoDAO.buscar(rs.getLong("NUMERO_PEDIDO")));

                pagamentos.add(pagamento);
            }

            return pagamentos;
        });
    }

}