package br.com.ifeira.entrega.entity;

import br.com.ifeira.compra.shared.entity.Pessoa;
import br.com.ifeira.entrega.enums.StatusEntrega;

import java.util.Date;


public class Entrega {

    Pessoa entregador;
    Date dataRealizacao;
    StatusEntrega statusEntrega;

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

    public boolean Entregue(){
        return false;
    }

    public boolean atribuirEntrega(){
        return false;
    }
    public boolean cancelarEntrega(){
        return false;
    }

}
