package br.com.ifeira.compra.shared.entity;

import java.util.ArrayList;
import java.util.List;

public class Feira {

    private String nome;
    private Endereco endereco;
    private String context;
    private String latitute;
    private String longitude;
    private List<ProdutoFeira> produtosFeira;

    public Feira(String nome, Endereco endereco, String context, String latitute, String longitude, List<ProdutoFeira> produtosFeira) {
        this.nome = nome;
        this.endereco = endereco;
        this.context = context;
        this.latitute = latitute;
        this.longitude = longitude;
        this.produtosFeira = produtosFeira;
    }

    public Feira() {
        this.produtosFeira = new ArrayList<>();
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
