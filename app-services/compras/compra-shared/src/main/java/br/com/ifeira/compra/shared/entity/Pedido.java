package br.com.ifeira.compra.shared.entity;

import br.com.ifeira.compra.shared.enums.StatusPedido;

import java.util.Date;

public class Pedido {

    private Date data;
    private StatusPedido statusPedido;
    private Long numeroPedido;
    private Date dataEntrega;
    private Cupom cupom;
    private Pessoa cliente;
    private Carrinho carrinho;
    private String cobranca;

    public Pedido(Date data, StatusPedido statusPedido, Long numeroPedido, Date dataEntrega, Cupom cupom, Pessoa cliente, Carrinho carrinho, String cobranca) {
        this.data = data;
        this.statusPedido = statusPedido;
        this.numeroPedido = numeroPedido;
        this.dataEntrega = dataEntrega;
        this.cupom = cupom;
        this.cliente = cliente;
        this.carrinho = carrinho;
        this.cobranca = cobranca;
    }

    public Pedido() {
    }

    public String getCobranca() {
        return cobranca;
    }

    public void setCobranca(String cobranca) {
        this.cobranca = cobranca;
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

    public Long getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(Long numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Cupom getCupom() {
        return cupom;
    }

    public void setCupom(Cupom cupom) {
        this.cupom = cupom;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

}
