package br.com.Ifeira.entrega.controller;

import br.com.Ifeira.compra.dao.shared.PagamentoDAO;
import br.com.Ifeira.compra.dao.shared.Persistivel;
import br.com.Ifeira.compra.entity.shared.Pessoa;
import br.com.Ifeira.compra.utils.shared.Notificavel;
import br.com.Ifeira.entrega.entity.Entrega;
import br.com.Ifeira.entrega.factory.EntregaFactory;
import br.com.Ifeira.entrega.factory.EntregaSimplesFactory;


public class EntregaController {
    Notificavel notificavel;
    Persistivel persistivel;

    EntregaController entregaController;
    EntregaFactory entregaFactory;

    public Entrega realizarEntrega(Entrega entrega){
        return null;
    }

    public EntregaSimplesFactory buscarEntregas(EntregaSimplesFactory entregaSimplesFactory){
        return null;
    }

    public Pessoa selecionarEntregador(Pessoa entregador){
        return null;
    }

    public Entrega gerarEntrega(PagamentoDAO pagamoentoDTO){
        return null;
    }

    public boolean atualizarSaldo(){
        return false;
    }

    public EntregaController getInstance (){
        return null;
    }

}
