package br.com.ifeira.compra.shared.entity;

public class ProdutoFeira {

    private Produto produto;
    private Feira feira;
    private Double preco;
    private Boolean ativo;

    public ProdutoFeira(Produto produto, Double preco, Feira feira, Boolean ativo) {
        this.produto = produto;
        this.preco = preco;
        this.feira = feira;
        this.ativo = ativo;
    }

    public ProdutoFeira() {
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Feira getFeira() {
        return feira;
    }

    public void setFeira(Feira feira) {
        this.feira = feira;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

}
