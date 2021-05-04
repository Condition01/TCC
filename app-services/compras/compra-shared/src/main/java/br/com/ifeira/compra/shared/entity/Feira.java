package br.com.ifeira.compra.shared.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Feira {

    private String nome;
    private Endereco endereco;
    private String context;
    private String latitute;
    private String longitude;
    private Integer diaEntrega;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<ProdutoFeira> produtosFeira;

    public Feira(String nome, Endereco endereco, String context, String latitute, String longitude, Integer diaEntrega, List<ProdutoFeira> produtosFeira) {
        this.nome = nome;
        this.endereco = endereco;
        this.context = context;
        this.latitute = latitute;
        this.longitude = longitude;
        this.produtosFeira = produtosFeira;
        this.diaEntrega = diaEntrega;
    }

    public Feira() {
        this.produtosFeira = new ArrayList<>();
    }

    public Integer getDiaEntrega() {
        return diaEntrega;
    }

    public void setDiaEntrega(Integer diaEntrega) {
        this.diaEntrega = diaEntrega;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getLatitute() {
        return latitute;
    }

    public void setLatitute(String latitute) {
        this.latitute = latitute;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public List<ProdutoFeira> getProdutosFeira() {
        return produtosFeira;
    }

    public void setProdutosFeira(List<ProdutoFeira> produtosFeira) {
        this.produtosFeira = produtosFeira;
    }

}
