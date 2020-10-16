package br.com.ifeira.compras.service;

import br.com.ifeira.compras.dao.ProdutoQuantidadeDAO;
import br.com.ifeira.compras.model.Produto;
import br.com.ifeira.compras.model.ProdutoQuantidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoQuantidadeService {
    @Autowired
    private ProdutoQuantidadeDAO pqDAO;
    
    public void guardarDados(List<Produto> produtos, List<Integer> qtds){
        for (Produto produto: produtos) {
            Optional<Integer> quantidade = qtds.stream().findFirst();
            for(Integer qtd: qtds){
                if(quantidade.isPresent()){
                    ProdutoQuantidade produtoQuantidade = new ProdutoQuantidade(produto,
                            quantidade.get().intValue());
                    pqDAO.save(produtoQuantidade);
                    qtds.remove(qtd);
                    break;
                }
            }
        }
    }
}
