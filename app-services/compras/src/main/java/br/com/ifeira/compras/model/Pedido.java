package br.com.ifeira.compras.model;

import br.com.ifeira.compras.enums.StatusPedido;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero")
    private Long numero;
    private Date data;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_cpf", referencedColumnName = "cpf")
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "entregador_cpf", referencedColumnName = "cpf")
    private Entregador entregador;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "numero_pagamento", referencedColumnName = "numero_pagamento")
    private Pagamento pagamento;

    @OneToOne(mappedBy = "pedido")
    private Reclamacao reclamacao;

    @OneToMany
    private List<ProdutoQuantidade> listaProdutos = new ArrayList<ProdutoQuantidade>();

    public Pedido(Long numero,
                  Date data,
                  Cliente cliente,
                  StatusPedido statusPedido,
                  Entregador entregador,
                  Pagamento pagamento,
                  Reclamacao reclamacao,
                  List<ProdutoQuantidade> listaProdutoQuantidade) {
        this.numero = numero;
        this.data = data;
        this.cliente = cliente;
        this.statusPedido = statusPedido;
        this.entregador = entregador;
        this.pagamento = pagamento;
        this.listaProdutos = listaProdutoQuantidade;
        this.reclamacao = reclamacao;
    }

    public Pedido() {
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
}