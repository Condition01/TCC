package br.com.ifeira.compra.shared.entity;

public class Endereco {

    private String numero;
    private String cep;
    private String complemento;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String UF;

    public Endereco(String numero, String cep, String complemento, String logradouro, String bairro, String cidade, String UF) {
        this.numero = numero;
        this.cep = cep;
        this.complemento = complemento;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.UF = UF;
    }

    public Endereco() {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
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

}
