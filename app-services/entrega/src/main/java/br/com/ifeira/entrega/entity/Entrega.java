package br.com.ifeira.entrega.entity;

import br.com.ifeira.compra.shared.entity.Pedido;
import br.com.ifeira.entrega.enums.StatusEntrega;

import java.util.Date;


public class Entrega {

    private Long id;
    private Entregador entregador;
    private Date dataRealizacao;
    private StatusEntrega statusEntrega;
    private Pedido pedido;

    public Entrega(Long id, Entregador entregador, Date dataRealizacao, StatusEntrega statusEntrega, Pedido pedido) {
        this.entregador = entregador;
        this.dataRealizacao = dataRealizacao;
        this.statusEntrega = statusEntrega;
        this.pedido = pedido;
        this.id = id;
    }

    public Entrega() {
    }

    public void marcarCancelada() {
        this.statusEntrega = StatusEntrega.CANCELADA;
    }

    public void marcarRealizada() {
        this.statusEntrega = StatusEntrega.REALIZADA;
    }

    public void marcarAtribuida() {
        this.statusEntrega = StatusEntrega.ATRIBUIDA;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    public Date getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(Date dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public StatusEntrega getStatusEntrega() {
        return statusEntrega;
    }

    public void setStatusEntrega(StatusEntrega statusEntrega) {
        this.statusEntrega = statusEntrega;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return "Entrega{" +
                "id=" + id +
                ", entregador=" + entregador +
                ", dataRealizacao=" + dataRealizacao +
                ", statusEntrega=" + statusEntrega +
                ", pedido=" + pedido +
                '}';
    }

}
