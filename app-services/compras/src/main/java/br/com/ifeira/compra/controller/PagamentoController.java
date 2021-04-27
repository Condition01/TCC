package br.com.ifeira.compra.controller;

import br.com.ifeira.compra.entity.Pagamento;
import br.com.ifeira.compra.shared.entity.Carrinho;
import br.com.ifeira.compra.shared.utils.Notificavel;
import org.springframework.stereotype.Component;

@Component
public class PagamentoController {
    private Notificavel notificavel;

    public Pagamento enfileirarPagamento(){
        return null;
    }

    public Pagamento gerarPagamento(Carrinho carrinho){
        return null;
    }

    public void notificar(){

    }
}
