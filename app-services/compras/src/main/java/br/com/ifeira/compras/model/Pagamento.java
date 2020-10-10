package br.com.ifeira.compras.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Pagamento {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroPagamento;
    private Double valor;
    private String numeroCartao;
    private String nomeCartao;
    private Date validadeCartao;
    private String codCartao;

    public Pagamento(Long numeroPagamento, Double valor, String numeroCartao, String nomeCartao, Date validadeCartao, String codCartao) {
        this.numeroPagamento = numeroPagamento;
        this.valor = valor;
        this.numeroCartao = numeroCartao;
        this.nomeCartao = nomeCartao;
        this.validadeCartao = validadeCartao;
        this.codCartao = codCartao;
    }

    public Pagamento() {
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
}
