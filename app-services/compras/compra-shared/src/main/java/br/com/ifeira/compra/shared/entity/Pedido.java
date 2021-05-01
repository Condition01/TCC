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
    private Double valorTotal;

    public Pedido(Date data, StatusPedido statusPedido, Long numeroPedido, Date dataEntrega, Cupom cupom, Pessoa cliente, Carrinho carrinho, String cobranca, Double valorTotal) {
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

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
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

    public void calcularValorTotal() {
        Double valor = 0.0;
        for(ProdutoQuantidade prodQtd: this.getCarrinho().getListaProdutoQuantidade()) {
            valor += (prodQtd.getProdutoFeira().getPreco() * prodQtd.getQuantidade());
        }
        if(this.cupom != null && this.cupom.getAtivo()) {
            valor = valor - (valor*this.cupom.getPorcentagem()/100);
        }
        this.valorTotal = round(valor, 2);
    }

    public static double round(double value, int places) {
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
