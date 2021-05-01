package br.com.ifeira.auth.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "ENDERECO")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Endereco.class)
public class Endereco {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 20, name = "NUMERO")
    private String numero;
    @Column(length = 20, name = "CEP")
    private String cep;
    @Column(length = 20, name = "COMPLEMENTO")
    private String complemento;
    @Column(length = 20, name = "BAIRRO")
    private String bairro;
    @Column(length = 20, name = "CIDADE")
    private String cidade;
    @Column(length = 20, name = "UF")
    private String uf;
    @Column(length = 20, name = "LOGRADOURO")
    private String logradouro;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(mappedBy = "endereco",fetch = FetchType.LAZY)
    private Pessoa pessoa;

    public Endereco(Integer id, String numero, String cep, String complemento, String bairro, String cidade, String logradouro, String uf, Pessoa pessoa) {
        this.id = id;
        this.numero = numero;
        this.cep = cep;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.logradouro = logradouro;
        this.uf = uf;
        this.pessoa = pessoa;
    }

    public Endereco() {
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

    public String getLogradouro() {
        return logradouro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
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

    public Pessoa getUsuario() {
        return pessoa;
    }

    public void setUsuario(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

}