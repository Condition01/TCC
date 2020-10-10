package br.com.ifeira.compras.model;

import br.com.ifeira.compras.model.Pedido;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Reclamacao {

    @Id  @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long IdReclamacao;
    @OneToOne
    private Pedido pedido;
    private String reclamacao;

    public Reclamacao(Long idReclamacao, Pedido pedido, String reclamacao) {
        IdReclamacao = idReclamacao;
        this.pedido = pedido;
        this.reclamacao = reclamacao;
    }

    public Reclamacao() {
    }

    public Long getIdReclamacao() {
        return IdReclamacao;
    }

    public void setIdReclamacao(Long idReclamacao) {
        IdReclamacao = idReclamacao;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getReclamacao() {
        return reclamacao;
    }

    public void setReclamacao(String reclamacao) {
        this.reclamacao = reclamacao;
    }
}
