package br.com.ifeira.compra.shared.entity;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private List<ProdutoQuantidade> listaProdutoQuantidade = new ArrayList<ProdutoQuantidade>();

    public Carrinho(Double valorTotal, List<ProdutoQuantidade> listaProdutoQuantidade) {
        this.listaProdutoQuantidade = listaProdutoQuantidade;
    }

    public Carrinho() {
    }

    public List<ProdutoQuantidade> getListaProdutoQuantidade() {
        return listaProdutoQuantidade;
    }

    public void setListaProdutoQuantidade(List<ProdutoQuantidade> listaProdutoQuantidade) {
        this.listaProdutoQuantidade = listaProdutoQuantidade;
    }

}
