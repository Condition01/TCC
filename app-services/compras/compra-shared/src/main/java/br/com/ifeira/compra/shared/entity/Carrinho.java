package br.com.ifeira.compra.shared.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private List<ProdutoQuantidade> listaProdutoQuantidade = new ArrayList<ProdutoQuantidade>();
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long pedidoRef;

    public Carrinho(List<ProdutoQuantidade> listaProdutoQuantidade) {
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

    public Long getPedidoRef() {
        return pedidoRef;
    }

    public void setPedidoRef(Long pedidoRef) {
        this.pedidoRef = pedidoRef;
    }

    public boolean vazio() {
        return this.getListaProdutoQuantidade().size() == 0;
    }

}
