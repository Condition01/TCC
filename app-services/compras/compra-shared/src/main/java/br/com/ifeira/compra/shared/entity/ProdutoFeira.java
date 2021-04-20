package br.com.Ifeira.compra.entity.shared;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoFeira {
    private Produto produto;
    private ProdutoQuantidade produtoQuantidade;
    private BigDecimal preco;
    private Feira feira;
    private boolean ativo;
    List<ProdutoFeira> produtoFeira = new ArrayList<ProdutoFeira>();

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ProdutoQuantidade getProdutoQuantidade() {
        return produtoQuantidade;
    }

    public void setProdutoQuantidade(ProdutoQuantidade produtoQuantidade) {
        this.produtoQuantidade = produtoQuantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Feira getFeira() {
        return feira;
    }

    public void setFeira(Feira feira) {
        this.feira = feira;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<ProdutoFeira> getProdutoFeira() {
        return produtoFeira;
    }

    public void setProdutoFeira(List<ProdutoFeira> produtoFeira) {
        this.produtoFeira = produtoFeira;
    }
}
