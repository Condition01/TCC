package br.com.ifeira.compras.model;

import br.com.ifeira.compras.enums.TipoProduto;

import javax.persistence.*;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cod_produto")
    private Long codProduto;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoProduto tpProduto;

    private String descricao;

    private double preco;

    @OneToOne(mappedBy = "produto")
    private ProdutoQuantidade produtoQuantidade;

    public Produto(Long codProduto,
                   String nome,
                   TipoProduto tpProduto,
                   String descricao,
                   double preco,
                   ProdutoQuantidade produtoQuantidade) {
        this.codProduto = codProduto;
        this.nome = nome;
        this.tpProduto = tpProduto;
        this.descricao = descricao;
        this.preco = preco;
        this.produtoQuantidade = produtoQuantidade;
    }

    public Produto() {
    }

    public ProdutoQuantidade getProdutoQuantidade() {
        return produtoQuantidade;
    }

    public void setProdutoQuantidade(ProdutoQuantidade produtoQuantidade) {
        this.produtoQuantidade = produtoQuantidade;
    }

    public Long getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(Long codProduto) {
        this.codProduto = codProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoProduto getTpProduto() {
        return tpProduto;
    }

    public void setTpProduto(TipoProduto tpProduto) {
        this.tpProduto = tpProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
