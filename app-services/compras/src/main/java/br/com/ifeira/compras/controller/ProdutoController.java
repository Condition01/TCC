package br.com.ifeira.compras.controller;

import br.com.ifeira.compras.model.Produto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class ProdutoController {
    @RequestMapping("/listarProdutostos")
    public List<Produto> listarProdutos(){
        return null;
    }

    @RequestMapping("/buscarProduto")
    public Produto buscarProduto(String id){
        return null;
    }
}
