package br.com.ifeira.compras.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Pagamento {
    @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroPagamento;
    private Double valor;
    private String numeroCartao;
    private String nomeCartao;
    private Date validadeCartao;
    private String codCartao;

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
}
