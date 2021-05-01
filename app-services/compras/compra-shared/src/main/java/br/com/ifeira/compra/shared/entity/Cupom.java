package br.com.ifeira.compra.shared.entity;

public class Cupom {

    private String nome;
    private Double porcentagem;
    private Boolean ativo;

    public Cupom(String nome, Double porcentagem, Boolean ativo) {
        this.nome = nome;
        this.porcentagem = porcentagem;
        this.ativo = ativo;
    }

    public Cupom() {
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(Double porcentagem) {
        this.porcentagem = porcentagem;
    }

}
