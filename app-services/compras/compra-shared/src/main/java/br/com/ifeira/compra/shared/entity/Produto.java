package br.com.ifeira.compra.shared.entity;

import br.com.ifeira.compra.shared.enums.TipoProduto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Produto {

    private String nome;
    private String descricao;
    private String codProduto;
    private TipoProduto tipoProduto;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<ProdutoFeira> produtosFeira;
    private String unidadeMedida;

    public Produto(String nome, String descricao, String codProduto, TipoProduto tipoProduto, List<ProdutoFeira> produtosFeira, String unidadeMedida) {
        this.nome = nome;
        this.descricao = descricao;
        this.codProduto = codProduto;
        this.tipoProduto = tipoProduto;
        this.produtosFeira = produtosFeira;
        this.unidadeMedida = unidadeMedida;
    }

    public Produto() {
        this.produtosFeira = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(String codProduto) {
        this.codProduto = codProduto;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public List<ProdutoFeira> getProdutosFeira() {
        return produtosFeira;
    }

    public void setProdutosFeira(List<ProdutoFeira> produtosFeira) {
        this.produtosFeira = produtosFeira;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

}
