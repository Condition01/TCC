package br.com.ifeira.compras.model;

import br.com.ifeira.compras.enums.TipoProduto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "tbl_produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cod_produto")
    private Long codProduto;

    private String nome;

    @Enumerated
    private TipoProduto tpProduto;

    private String descricao;

    private double preco;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "produto")
    private List<ProdutoQuantidade> produtosQuantidade;

    public Produto(Long codProduto,
                   String nome,
                   TipoProduto tpProduto,
                   String descricao,
                   double preco,
                   List<ProdutoQuantidade> produtosQuantidade) {
        this.codProduto = codProduto;
        this.nome = nome;
        this.tpProduto = tpProduto;
        this.descricao = descricao;
        this.preco = preco;
        this.produtosQuantidade = produtosQuantidade;
    }

    public void adicionarProdutoQuantidade(ProdutoQuantidade pedido) {
        this.produtosQuantidade.add(pedido);
    }

    public void removerProdutoQuantidade(Long id) throws Exception {

        Optional<ProdutoQuantidade> optionalProdutoQuantidade = this.produtosQuantidade.stream()
                .parallel()
                .filter(produtoQtd -> produtoQtd.getId() == id)
                .findFirst();
        if (optionalProdutoQuantidade.isPresent()) {
            ProdutoQuantidade produtoQtdRemovido = optionalProdutoQuantidade.get();
            this.produtosQuantidade.remove(produtoQtdRemovido);
        } else {
            throw new Exception("Produto não encontrado para remoção");
        }
    }

    public Produto() {
    }

    public TipoProduto getTpProduto() {
        return tpProduto;
    }

    public void setTpProduto(TipoProduto tpProduto) {
        this.tpProduto = tpProduto;
    }

    public List<ProdutoQuantidade> getProdutosQuantidade() {
        return produtosQuantidade;
    }

    public void setProdutosQuantidade(List<ProdutoQuantidade> produtosQuantidade) {
        this.produtosQuantidade = produtosQuantidade;
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
