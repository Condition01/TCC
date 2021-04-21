package br.com.ifeira.pagamento.shared.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PagamentoDTO {

    private String cpfCliente;
    private long numeroPedido;
    private String numeroCartao;
    private String validadeCartao;
    private String status;
    private Date data;
    private String cvv;
    private String nomeCartao;
    private BigDecimal valorTotalPedido;

    public PagamentoDTO(String cpfCliente, long numeroPedido, String numeroCartao, String validadeCartao, String status, Date data, String cvv, String nomeCartao, BigDecimal valorTotalPedido) {
        this.cpfCliente = cpfCliente;
        this.numeroPedido = numeroPedido;
        this.numeroCartao = numeroCartao;
        this.validadeCartao = validadeCartao;
        this.status = status;
        this.data = data;
        this.cvv = cvv;
        this.nomeCartao = nomeCartao;
        this.valorTotalPedido = valorTotalPedido;
    }

    public PagamentoDTO() {
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public long getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(long numeroPedido) {
        this.numeroPedido = numeroPedido;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }

    public BigDecimal getValorTotalPedido() {
        return valorTotalPedido;
    }

    public void setValorTotalPedido(BigDecimal valorTotalPedido) {
        this.valorTotalPedido = valorTotalPedido;
    }

    @Override
    public String toString() {
        return "PagamentoDTO{" +
                "cpfCliente='" + cpfCliente + '\'' +
                ", numeroPedido=" + numeroPedido +
                ", numeroCartao='" + numeroCartao + '\'' +
                ", validadeCartao='" + validadeCartao + '\'' +
                ", status='" + status + '\'' +
                ", data=" + data +
                ", cvv='" + cvv + '\'' +
                ", nomeCartao='" + nomeCartao + '\'' +
                ", valorTotalPedido=" + valorTotalPedido +
                '}';
    }
}
