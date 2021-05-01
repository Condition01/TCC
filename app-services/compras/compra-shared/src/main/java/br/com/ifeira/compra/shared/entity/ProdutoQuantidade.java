package br.com.ifeira.compra.shared.entity;

public class ProdutoQuantidade {

    private ProdutoFeira produtoFeira;
    private Integer quantidade;

    public ProdutoQuantidade(ProdutoFeira produtoFeira, Integer quantidade) {
        this.produtoFeira = produtoFeira;
        this.quantidade = quantidade;
    }

    public ProdutoQuantidade() {
    }

    public ProdutoFeira getProdutoFeira() {
        return produtoFeira;
    }

    public void setProdutoFeira(ProdutoFeira produtoFeira) {
        this.produtoFeira = produtoFeira;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}
