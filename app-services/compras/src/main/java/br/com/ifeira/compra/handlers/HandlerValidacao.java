package br.com.ifeira.compra.handlers;

import br.com.ifeira.compra.entity.Pagamento;
import br.com.ifeira.compra.shared.dao.CupomDAO;
import br.com.ifeira.compra.shared.dao.Persistivel;
import br.com.ifeira.compra.shared.dao.PersistivelContextual;
import br.com.ifeira.compra.shared.dao.ProdutoFeiraDAO;
import br.com.ifeira.compra.shared.entity.Cupom;
import br.com.ifeira.compra.shared.entity.ProdutoFeira;
import br.com.ifeira.compra.shared.entity.ProdutoQuantidade;
import br.com.ifeira.compra.shared.exceptions.BusinessViolationException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class HandlerValidacao extends PagamentoInBaseHandler {

    private PersistivelContextual<ProdutoFeira, String> produtoFeiraDAO;
    private Persistivel<Cupom, String> cupomDAO;
    private JdbcTemplate jdbcTemplate;

    public HandlerValidacao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.produtoFeiraDAO = new ProdutoFeiraDAO(jdbcTemplate);
        this.cupomDAO = new CupomDAO(jdbcTemplate);
    }

    public Boolean validarCobranca(String cobranca) {
        cobranca += "%";
        String finalCobranca = cobranca;
        return this.jdbcTemplate.query("SELECT NUMERO FROM PEDIDO p WHERE COBRANCA LIKE ?", ps -> {
            ps.setString(1, finalCobranca);
        }, rs -> {
            Boolean valido = true;
            while (rs.next()) {
                Long numero = rs.getLong("NUMERO");
                valido = numero == null ? true : false;
            }
            return valido;
        });
    }

    public Boolean validarDadosPag(Pagamento pagamento) {
        return (pagamento.getCvv() != null && !pagamento.getCvv().equals("")
            && (pagamento.getNumeroCartao() != null && !pagamento.getNumeroCartao().equals(""))
            && pagamento.getValidadeCartao() != null && !pagamento.getValidadeCartao().equals(""));
    }

    @Override
    public Pagamento handle(Pagamento pagamento) throws Exception {
        Boolean validoCobranca = validarCobranca(pagamento.getPedido().getCobranca());
        Boolean validaDadosPag = validarDadosPag(pagamento);

        if(!validaDadosPag) throw new BusinessViolationException("Dados de pagamento inválidos!");
        if(!validoCobranca) throw new BusinessViolationException("Esse pedido já foi feito!");

        List<ProdutoQuantidade> prodQtdList = new ArrayList<>();
        for(ProdutoQuantidade pagQtd: pagamento.getPedido().getCarrinho().getListaProdutoQuantidade()) {
            ProdutoFeira produtoFeira = this.produtoFeiraDAO
                    .buscar(pagQtd.getProdutoFeira().getProduto().getCodProduto(),
                            pagQtd.getProdutoFeira().getFeira().getContext());
            ProdutoQuantidade prodQtd = new ProdutoQuantidade();
            prodQtd.setProdutoFeira(produtoFeira);
            prodQtd.setQuantidade(pagQtd.getQuantidade());

            prodQtdList.add(prodQtd);
        }
        Cupom cupom = pagamento.getPedido().getCupom();

        cupom = cupom != null? this.cupomDAO.buscar(pagamento.getPedido().getCupom().getNome()) : null;

        Double valorTotal = calcularValorTotal(prodQtdList, cupom);

        if(!valorTotal.equals(pagamento.getPedido().getValorTotal())) {
            throw new BusinessViolationException("Valores inválidos");
        }

        return getNext().handle(pagamento);
    }

    public Double calcularValorTotal(List<ProdutoQuantidade> prodQtdList, Cupom cupom) {
        Double valor = 0.0;
        for(ProdutoQuantidade prodQtd: prodQtdList) {
            valor += (prodQtd.getProdutoFeira().getPreco() * prodQtd.getQuantidade());
        }
        if(cupom != null && cupom.getAtivo()) {
            valor = valor - (valor*cupom.getPorcentagem()/100);
        }
        return round(valor, 2);
    }

    public double round(double value, int places) {
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
