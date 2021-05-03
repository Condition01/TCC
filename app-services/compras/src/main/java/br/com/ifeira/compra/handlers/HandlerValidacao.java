package br.com.ifeira.compra.handlers;

import br.com.ifeira.compra.entity.Pagamento;
import br.com.ifeira.compra.shared.dao.CupomDAO;
import br.com.ifeira.compra.shared.dao.Persistivel;
import br.com.ifeira.compra.shared.dao.PersistivelContextual;
import br.com.ifeira.compra.shared.dao.ProdutoFeiraDAO;
import br.com.ifeira.compra.shared.entity.Cupom;
import br.com.ifeira.compra.shared.entity.ProdutoFeira;
import br.com.ifeira.compra.shared.entity.ProdutoQuantidade;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class HandlerValidacao extends PagamentoInBaseHandler {

    private PersistivelContextual<ProdutoFeira, String> produtoFeiraDAO;
    private Persistivel<Cupom, String> cupomDAO;

    public HandlerValidacao(JdbcTemplate jdbcTemplate) {
        this.produtoFeiraDAO = new ProdutoFeiraDAO(jdbcTemplate);
        this.cupomDAO = new CupomDAO(jdbcTemplate);
    }

    @Override
    public PagamentoDTO handle(Pagamento pagamento) throws Exception {
        List<ProdutoQuantidade> prodQtdList = new ArrayList<>();
        for(ProdutoQuantidade pagQtd: pagamento.getPedido().getCarrinho().getListaProdutoQuantidade()) {
            ProdutoFeira produtoFeira = this.produtoFeiraDAO
                    .buscar(pagQtd.getProdutoFeira().getProduto().getCodProduto(),
                            pagQtd.getProdutoFeira().getFeira().getContext());
            ProdutoQuantidade prodQtd = new ProdutoQuantidade();
            prodQtd.setProdutoFeira(produtoFeira);
            prodQtd.setQuantidade(prodQtd.getQuantidade());

            prodQtdList.add(prodQtd);
        }
        Cupom cupom = this.cupomDAO.buscar(pagamento.getPedido().getCupom().getNome());

        Double valorTotal = calcularValorTotal(prodQtdList, cupom);

        if(valorTotal != pagamento.getPedido().getValorTotal()) {
            throw new Exception("Valore de pedido inválido");
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
