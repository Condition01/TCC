package br.com.ifeira.pagamento.shared.dto;

import java.util.Date;

public class PagamentoDTO {

    private Long idPagamento;
    private Long numeroPedido;
    private String cpfCliente;
    private String email;
    private String idCobranca;
    private String numeroCartao;
    private String validadeCartao;
    private String credId;
    private String status;
    private Date data;
    private String cvv;
    private String nomeCartao;
    private Double valorTotalPedido;
    private String rua;
    private String numeroCasa;
    private String complemento;
    private String bairro;
    private String cidade;
    private String UF;
    private String codigoPostal;

    public PagamentoDTO(Long idPagamento, String cpfCliente, String email, String idCobranca, Long numeroPedido, String numeroCartao, String validadeCartao, String credId, String status, Date data, String cvv, String nomeCartao, Double valorTotalPedido, String rua, String numeroCasa, String complemento, String bairro, String cidade, String UF, String codigoPostal) {
        this.idPagamento = idPagamento;
        this.cpfCliente = cpfCliente;
        this.email = email;
        this.idCobranca = idCobranca;
        this.numeroPedido = numeroPedido;
        this.numeroCartao = numeroCartao;
        this.validadeCartao = validadeCartao;
        this.credId = credId;
        this.status = status;
        this.data = data;
        this.cvv = cvv;
        this.nomeCartao = nomeCartao;
        this.valorTotalPedido = valorTotalPedido;
        this.rua = rua;
        this.numeroCasa = numeroCasa;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.UF = UF;
        this.codigoPostal = codigoPostal;
    }

    public PagamentoDTO() {
    }

    public Long getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Long idPagamento) {
        this.idPagamento = idPagamento;
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

    public Double getValorTotalPedido() {
        return valorTotalPedido;
    }

    public void setValorTotalPedido(Double valorTotalPedido) {
        this.valorTotalPedido = valorTotalPedido;
    }

    public String getIdCobranca() {
        return idCobranca;
    }

    public void setIdCobranca(String idCobranca) {
        this.idCobranca = idCobranca;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCredId() {
        return credId;
    }

    public void setCredId(String credId) {
        this.credId = credId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Override
    public String toString() {
        return "PagamentoDTO{" +
                "cpfCliente='" + cpfCliente + '\'' +
                ", email='" + email + '\'' +
                ", idCobranca='" + idCobranca + '\'' +
                ", numeroPedido=" + numeroPedido +
                ", numeroCartao='" + numeroCartao + '\'' +
                ", validadeCartao='" + validadeCartao + '\'' +
                ", credHash='" + credId + '\'' +
                ", status='" + status + '\'' +
                ", data=" + data +
                ", cvv='" + cvv + '\'' +
                ", nomeCartao='" + nomeCartao + '\'' +
                ", valorTotalPedido=" + valorTotalPedido +
                ", rua='" + rua + '\'' +
                ", numeroCasa='" + numeroCasa + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", UF='" + UF + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                '}';
    }

}
