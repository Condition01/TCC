package br.com.ifeira.compras.service;

import br.com.ifeira.compras.dao.PagamentoDAO;
import br.com.ifeira.compras.model.Pagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PagamentoService {

    @Autowired
    private PagamentoDAO pagamentoDAO;

    public List<Pagamento> listarPagamentos(){
        return this.pagamentoDAO.findAll();
    }

    public Optional<Pagamento> identificarPagamento(Long numeroPagamento){
        return this.pagamentoDAO.findById(numeroPagamento);
    }

    public Pagamento deletarPagamento(Long numeroPagamento){
        Optional<Pagamento> optPagamento = this.pagamentoDAO.findById(numeroPagamento);
        if(optPagamento.isPresent()){
            this.pagamentoDAO.delete(optPagamento.get());
        }
        return optPagamento.get();
    }

    public void inserirPagamento(Pagamento pagamento){
        this.pagamentoDAO.save(pagamento);
    }
}

