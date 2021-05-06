package br.com.ifeira.compra.dao;

import br.com.ifeira.compra.entity.Pagamento;
import br.com.ifeira.compra.shared.dao.Persistivel;
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

    public PagamentoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Pagamento salvar(Pagamento item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql
                = "INSERT INTO PAGAMENTO\n" +
                "(NUMERO_PEDIDO, NUMERO_CARTAO, VALIDADE_CARTAO, CVV, DATA_PAGAMENTO)\n" +
                "VALUES(?, ?, ?, ?, ?)";

        List<SqlParameter> declaredParams = new ArrayList<>();

        declaredParams.add(new SqlParameter(Types.BIGINT));
        declaredParams.add(new SqlParameter(Types.BLOB));
        declaredParams.add(new SqlParameter(Types.BLOB));
        declaredParams.add(new SqlParameter(Types.BLOB));
        declaredParams.add(new SqlParameter(Types.DATE));

        byte[] numeroCartaoByte =  item.getNumeroCartao().getBytes(StandardCharsets.ISO_8859_1);
        byte[] validadeCartaoByte = item.getValidadeCartao().getBytes(StandardCharsets.ISO_8859_1);
        byte[] cvvByte = item.getCvv().getBytes(StandardCharsets.ISO_8859_1);

        PreparedStatementCreatorFactory pscFactory =
                new PreparedStatementCreatorFactory(sql, declaredParams);

        pscFactory.setReturnGeneratedKeys(true);

        this.jdbcTemplate.update(
                pscFactory.newPreparedStatementCreator(
                        new Object[] {
                                item.getPedido().getNumeroPedido(),
                                numeroCartaoByte,
                                validadeCartaoByte,
                                cvvByte,
                                item.getData()
                        }), keyHolder);

        Long primaryKey = keyHolder.getKey().longValue();
        item.setId(primaryKey);
        return item;
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

}