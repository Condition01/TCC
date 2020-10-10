package br.com.ifeira.compras.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_pagamento")
    private Long numeroPagamento;

    private Double valor;

    private String numeroCartao;

    private String nomeCartao;

    private Date validadeCartao;

    private String codCartao;

    @OneToOne(mappedBy = "pagamento")
    private Pedido pedido;

    public Pagamento(Long numeroPagamento,
                     Double valor,
                     String numeroCartao,
                     String nomeCartao,
                     Date validadeCartao,
                     String codCartao,
                     Pedido pedido) {
        this.numeroPagamento = numeroPagamento;
        this.valor = valor;
        this.numeroCartao = numeroCartao;
        this.nomeCartao = nomeCartao;
        this.validadeCartao = validadeCartao;
        this.codCartao = codCartao;
        this.pedido = pedido;
    }

    public Pagamento() {
    }

    public Long getNumeroPagamento() {
        return numeroPagamento;
    }

    public void setNumeroPagamento(Long numeroPagamento) {
        this.numeroPagamento = numeroPagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }

    public Date getValidadeCartao() {
        return validadeCartao;
    }

    public void setValidadeCartao(Date validadeCartao) {
        this.validadeCartao = validadeCartao;
    }

    public String getCodCartao() {
        return codCartao;
    }

    public void setCodCartao(String codCartao) {
        this.codCartao = codCartao;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
}
