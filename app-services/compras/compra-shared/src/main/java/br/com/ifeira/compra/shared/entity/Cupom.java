package br.com.ifeira.compra.shared.entity;

public class Cupom {

    private String descricao;
    private Double porcentagem;

    public Cupom(String descricao, Double porcentagem) {
        this.descricao = descricao;
        this.porcentagem = porcentagem;
    }

    public Cupom() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(Double porcentagem) {
        this.porcentagem = porcentagem;
    }

}
