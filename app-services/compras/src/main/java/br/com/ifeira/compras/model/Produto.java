package br.com.ifeira.compras.model;

import br.com.ifeira.compras.enums.TipoProduto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;

@Entity
@Table(name = "tbl_produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cod_produto")
    private Long codProduto;

    private String nome;

    @Enumerated
    private TipoProduto tp_Produto;

    private String descricao;

    private double preco;

    @JsonIgnore
    @OneToOne(mappedBy = "produto", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ProdutoQuantidade produtoQuantidade;

    public Produto(Long codProduto,
                   String nome,
                   TipoProduto tpProduto,
                   String descricao,
                   double preco,
                   ProdutoQuantidade produtoQuantidade) {
        this.codProduto = codProduto;
        this.nome = nome;
        this.tp_Produto = tp_Produto;
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
        return tp_Produto;
    }

    public void setTpProduto(TipoProduto tp_Produto) {
        this.tp_Produto = tp_Produto;
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
