package br.com.ifeira.compras.model;

import br.com.ifeira.compras.enums.StatusPedido;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.ifeira.compras.model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tbl_pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero")
    private Long numero;
    private Date data;

    @Enumerated
    private StatusPedido statusPedido;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "cliente_cpf", referencedColumnName = "cpf")
    private Usuario cliente;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pagamento pagamento;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(mappedBy = "pedido")
    private Reclamacao reclamacao;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ProdutoQuantidade> listaProdutos = new ArrayList<ProdutoQuantidade>();

    public Pedido(Long numero,
                  Date data,
                  StatusPedido statusPedido,
                  Usuario cliente,
                  Pagamento pagamento,
                  Reclamacao reclamacao,
                  List<ProdutoQuantidade> listaProdutos) {
        this.numero = numero;
        this.data = data;
        this.statusPedido = statusPedido;
        this.cliente = cliente;
        this.pagamento = pagamento;
        this.reclamacao = reclamacao;
        this.listaProdutos = listaProdutos;
    }

    public Pedido() {
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Reclamacao getReclamacao() {
        return reclamacao;
    }

    public void setReclamacao(Reclamacao reclamacao) {
        this.reclamacao = reclamacao;
    }

    public List<ProdutoQuantidade> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<ProdutoQuantidade> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }
}