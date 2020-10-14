package br.com.ifeira.compras.controller;

import br.com.ifeira.compras.model.Pedido;
import br.com.ifeira.compras.model.Produto;
import br.com.ifeira.compras.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.List;


@RestController
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @RequestMapping("/listarProdutostos")
    public String listarProdutos() {
        return "Funcionou";
    }



/*    @RequestMapping("/buscarProduto")
    public Produto buscarProduto(String id){
        return null;
    }*/
}
