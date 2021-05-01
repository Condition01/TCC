package br.com.ifeira.compra.controller;

import br.com.ifeira.compra.config.MailingConfig;
import br.com.ifeira.compra.dao.PagamentoDAO;
import br.com.ifeira.compra.entity.Pagamento;
import br.com.ifeira.compra.shared.dao.Persistivel;
import br.com.ifeira.compra.shared.entity.Carrinho;
import br.com.ifeira.compra.shared.utils.Notificavel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PagamentoController {

    @Autowired
    private MailingConfig mailingConfig;
    private Persistivel<Pagamento, Long> pagamentoDAO;
    private Notificavel notificavel;

    public PagamentoController(@Autowired JdbcTemplate jdbcTemplate) {
        this.pagamentoDAO = new PagamentoDAO(jdbcTemplate);
    }

    public Pagamento enfileirarPagamento(){
        return null;
    }

    public Pagamento gerarPagamento(Carrinho carrinho){
        return null;
    }

    public void notificar(){

    }

}
