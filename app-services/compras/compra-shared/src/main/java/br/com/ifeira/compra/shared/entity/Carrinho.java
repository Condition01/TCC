package br.com.ifeira.compra.shared.entity;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private Double valorTotal;
    private List<ProdutoQuantidade> listaProdutoQuantidade = new ArrayList<ProdutoQuantidade>();

    public Carrinho(Double valorTotal, List<ProdutoQuantidade> listaProdutoQuantidade) {
        this.valorTotal = valorTotal;
        this.listaProdutoQuantidade = listaProdutoQuantidade;
    }

    public Carrinho() {
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ProdutoQuantidade> getListaProdutoQuantidade() {
        return listaProdutoQuantidade;
    }

    public void setListaProdutoQuantidade(List<ProdutoQuantidade> listaProdutoQuantidade) {
        this.listaProdutoQuantidade = listaProdutoQuantidade;
    }

}
