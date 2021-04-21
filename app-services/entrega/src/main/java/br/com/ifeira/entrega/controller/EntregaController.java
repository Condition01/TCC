package br.com.ifeira.entrega.controller;

import br.com.ifeira.compra.shared.dao.PagamentoDAO;
import br.com.ifeira.compra.shared.dao.Persistivel;
import br.com.ifeira.compra.shared.entity.Pessoa;
import br.com.ifeira.compra.shared.utils.Notificavel;
import br.com.ifeira.entrega.entity.Entrega;
import br.com.ifeira.entrega.factory.EntregaFactory;
import br.com.ifeira.entrega.factory.EntregaSimplesFactory;


public class EntregaController {

    private Notificavel notificavel;
    private Persistivel persistivel;
    private EntregaController entregaController;
    private EntregaFactory entregaFactory;

    public EntregaController(Notificavel notificavel, Persistivel persistivel, EntregaController entregaController, EntregaFactory entregaFactory) {
        this.notificavel = notificavel;
        this.persistivel = persistivel;
        this.entregaController = entregaController;
        this.entregaFactory = entregaFactory;
    }

    public EntregaController() {
    }

    public Entrega realizarEntrega(Entrega entrega) {
        return null;
    }

    public EntregaSimplesFactory buscarEntregas(EntregaSimplesFactory entregaSimplesFactory) {
        return null;
    }

    public Pessoa selecionarEntregador(Pessoa entregador) {
        return null;
    }

    public Entrega gerarEntrega(PagamentoDAO pagamoentoDTO) {
        return null;
    }

    public boolean atualizarSaldo() {
        return false;
    }

    public EntregaController getInstance() {
        return null;
    }

}
