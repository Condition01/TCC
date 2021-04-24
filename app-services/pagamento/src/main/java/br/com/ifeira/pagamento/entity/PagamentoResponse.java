package br.com.ifeira.pagamento.entity;

import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;

import java.util.Date;

public class PagamentoResponse {

    private String cpfCliente;
    private Long numeroPedido;
    private String status;
    private Date data;
    private Double valorTotalPedido;
    private String idTrx;

    public PagamentoResponse(String cpfCliente, Long numeroPedido, String numeroCartao, String validadeCartao, String status, Date data, String cvv, String nomeCartao, Double valorTotalPedido, String idTrx) {
        this.cpfCliente = cpfCliente;
        this.numeroPedido = numeroPedido;
        this.status = status;
        this.data = data;
        this.valorTotalPedido = valorTotalPedido;
        this.idTrx = idTrx;
    }

    public PagamentoResponse() {
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public Long getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(Long numeroPedido) {
        this.numeroPedido = numeroPedido;
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

    public Double getValorTotalPedido() {
        return valorTotalPedido;
    }

    public void setValorTotalPedido(Double valorTotalPedido) {
        this.valorTotalPedido = valorTotalPedido;
    }

    public String getIdTrx() {
        return idTrx;
    }

    public void setIdTrx(String idTrx) {
        this.idTrx = idTrx;
    }

    public static PagamentoResponse dtoToResponse(PagamentoDTO pagamentoDTO, String statusPagamento, String idTrx) {
        PagamentoResponse returnedReponse = new PagamentoResponse();

        //TODO - PARSE DOS STATUS RETORNADOS POR API NO STATUS DE PAGAMENTO UTILIZANDO ENUM
        returnedReponse.setCpfCliente(pagamentoDTO.getCpfCliente());
        returnedReponse.setNumeroPedido(pagamentoDTO.getNumeroPedido());
        returnedReponse.setStatus(pagamentoDTO.getStatus()); // POSSIVELMENTE ALTERADO
        returnedReponse.setData(pagamentoDTO.getData());
        returnedReponse.setValorTotalPedido(pagamentoDTO.getValorTotalPedido());
        returnedReponse.setStatus(statusPagamento);

        returnedReponse.setIdTrx(idTrx);

        return returnedReponse;
    }
}
