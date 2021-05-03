package br.com.ifeira.compra.entity;

import br.com.ifeira.compra.shared.entity.Pedido;
import br.com.ifeira.compra.shared.enums.StatusPagamento;

import java.util.Date;

public class Pagamento {

    private StatusPagamento statusPagamento;
    private String numeroCartao;
    private String validadeCartao;
    private String cvv;
    private Date data;
    private Pedido pedido;
    private String creditCardHash;
    private String creditCardId;

    public Pagamento(StatusPagamento statusPagamento, String numeroCartao, String validadeCartao, String cvv, Date data, Pedido pedido, String creditCardHash, String creditCardId) {
        this.statusPagamento = statusPagamento;
        this.numeroCartao = numeroCartao;
        this.validadeCartao = validadeCartao;
        this.cvv = cvv;
        this.data = data;
        this.pedido = pedido;
        this.creditCardHash = creditCardHash;
        this.creditCardId = creditCardId;
    }

    public Pagamento() {
    }

    public String getCreditCardHash() {
        return creditCardHash;
    }

    public void setCreditCardHash(String creditCardHash) {
        this.creditCardHash = creditCardHash;
    }

    public String getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(String creditCardId) {
        this.creditCardId = creditCardId;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getValidadeCartao() {
        return validadeCartao;
    }

    public void setValidadeCartao(String validadeCartao) {
        this.validadeCartao = validadeCartao;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "statusPagamento=" + statusPagamento +
                ", data=" + data +
                ", pedido=" + pedido +
                '}';
    }
}
