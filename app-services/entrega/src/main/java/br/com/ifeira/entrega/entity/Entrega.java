package br.com.ifeira.entrega.entity;

import br.com.ifeira.compra.shared.entity.Pedido;
import br.com.ifeira.compra.shared.entity.Pessoa;
import br.com.ifeira.entrega.enums.StatusEntrega;

import java.util.Date;


public class Entrega {

    private Pessoa entregador;
    private Date dataRealizacao;
    private StatusEntrega statusEntrega;
    private Pedido pedido;

    public Entrega(Pessoa entregador, Date dataRealizacao, StatusEntrega statusEntrega, Pedido pedido) {
        this.entregador = entregador;
        this.dataRealizacao = dataRealizacao;
        this.statusEntrega = statusEntrega;
        this.pedido = pedido;
    }

    public Entrega() {
    }

    public void marcarRealizada() {
        this.statusEntrega = StatusEntrega.REALIZADA;
    }

    public void marcarAtribuida() {
        this.statusEntrega = StatusEntrega.ATRIBUIDA;
    }

    public void marcarCancelada() {
        this.statusEntrega = StatusEntrega.CANCELADA;
    }

    public void marcarPendente() {
        this.statusEntrega = StatusEntrega.PENDENTE;
    }

    public Pessoa getEntregador() {
        return entregador;
    }

    public void setEntregador(Pessoa entregador) {
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

}
