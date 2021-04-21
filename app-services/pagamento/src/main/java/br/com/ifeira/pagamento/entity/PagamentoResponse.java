package br.com.ifeira.pagamento.entity;

import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;

import java.math.BigDecimal;
import java.util.Date;

public class PagamentoResponse {


    private String cpfCliente;
    private long numeroPedido;
    private String numeroCartao;
    private String validadeCartao;
    private String status;
    private Date data;
    private String cvv;
    private String nomeCartao;
    private BigDecimal valorTotalPedido;
    private String statusResposta;
    private Long numeroTransacao;

    public PagamentoResponse(String cpfCliente, long numeroPedido, String numeroCartao, String validadeCartao, String status, Date data, String cvv, String nomeCartao, BigDecimal valorTotalPedido, String statusResposta, long numeroTransacao) {
        this.cpfCliente = cpfCliente;
        this.numeroPedido = numeroPedido;
        this.numeroCartao = numeroCartao;
        this.validadeCartao = validadeCartao;
        this.status = status;
        this.data = data;
        this.cvv = cvv;
        this.nomeCartao = nomeCartao;
        this.valorTotalPedido = valorTotalPedido;
        this.statusResposta = statusResposta;
        this.numeroTransacao = numeroTransacao;
    }

    public PagamentoResponse() {
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

    public String getStatusResposta() {
        return statusResposta;
    }

    public void setStatusResposta(String statusResposta) {
        this.statusResposta = statusResposta;
    }

    public Long getNumeroTransacao() {
        return numeroTransacao;
    }

    public void setNumeroTransacao(Long numeroTransacao) {
        this.numeroTransacao = numeroTransacao;
    }

    private PagamentoResponse dtoToResponse(PagamentoDTO pagamentoDTO, String statusPagamento, String statusResposta, Long numeroTransacao) {
        PagamentoResponse returnedReponse = new PagamentoResponse();

        //TODO - PARSE DOS STATUS RETORNADOS POR API NO STATUS DE PAGAMENTO UTILIZANDO ENUM
        returnedReponse.setCpfCliente(pagamentoDTO.getCpfCliente());
        returnedReponse.setNumeroPedido(pagamentoDTO.getNumeroPedido());
        returnedReponse.setValidadeCartao(pagamentoDTO.getValidadeCartao());
        returnedReponse.setStatus(pagamentoDTO.getStatus()); // POSSIVELMENTE ALTERADO
        returnedReponse.setData(pagamentoDTO.getData());
        returnedReponse.setCvv(pagamentoDTO.getCvv());
        returnedReponse.setNomeCartao(pagamentoDTO.getNomeCartao());
        returnedReponse.setValorTotalPedido(pagamentoDTO.getValorTotalPedido());

        returnedReponse.setStatusResposta(statusResposta);
        returnedReponse.setNumeroTransacao(numeroTransacao);

        return returnedReponse;
    }
}
