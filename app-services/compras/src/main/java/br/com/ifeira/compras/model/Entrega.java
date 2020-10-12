package br.com.ifeira.compras.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_entrega")
public class Entrega {
    @Id
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "entregador_id", referencedColumnName = "cpf")
    private Usuario entregador;

    public Entrega(long id, Usuario entregador) {
        this.id = id;
        this.entregador = entregador;
    }

    public Entrega() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getEntregador() {
        return entregador;
    }

    public void setEntregador(Usuario entregador) {
        this.entregador = entregador;
    }
}
