package br.com.ifeira.compras.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "tbl_produto_quantidade")
public class ProdutoQuantidade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @OneToOne(mappedBy = "pedido")

    @JoinColumn(name = "cod_produto", referencedColumnName = "cod_produto")
    @OneToOne(fetch = FetchType.EAGER)
    private Produto produto;

    private int quantidade;

    public ProdutoQuantidade(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ProdutoQuantidade() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
