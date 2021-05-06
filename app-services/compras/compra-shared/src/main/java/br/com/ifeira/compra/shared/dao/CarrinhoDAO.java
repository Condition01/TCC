package br.com.ifeira.compra.shared.dao;

import br.com.ifeira.compra.shared.entity.*;
import javafx.util.Pair;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.util.List;

public class CarrinhoDAO implements PersistivelContextual<Carrinho, Pair<Long, String>> {

    private JdbcTemplate jdbcTemplate;

    public CarrinhoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Carrinho salvar(Carrinho item, String contexto) {
        String sql
                = "INSERT INTO IFEIRA.CARRINHO\n" +
                "(COD_PRODUTO, CONTEXTO, QUANTIDADE, NUMERO_PEDIDO)\n" +
                "VALUES(?, ?, ?, ?);\n";

        List<ProdutoQuantidade> items = item.getListaProdutoQuantidade();

        items.forEach(element -> {
            Produto produto = element.getProdutoFeira().getProduto();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection
                        .prepareStatement(sql);
                ps.setString(1, produto.getCodProduto());
                ps.setString(2, contexto);
                ps.setInt(3, element.getQuantidade());
                ps.setLong(4, item.getPedidoRef());
                return ps;
            });
        });

        return item;
    }

    @Override
    public List<Carrinho> listar(String contexto) {
        return null;
    }

    @Override
    public Carrinho buscar(Pair<Long, String> key, String contexto) {
        return null;
    }

    @Override
    public List<Carrinho> buscarMultiplos(Pair<Long, String> key, String contexto) {
        return null;
    }

    @Override
    public Carrinho editar(Carrinho item) {
        return null;
    }

}
