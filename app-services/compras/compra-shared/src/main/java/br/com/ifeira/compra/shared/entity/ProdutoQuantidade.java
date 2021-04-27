package br.com.ifeira.compra.shared.entity;

public class ProdutoQuantidade {

    private Produto produto;
    private Integer quantidade;

    public ProdutoQuantidade(Produto produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ProdutoQuantidade() {
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}
