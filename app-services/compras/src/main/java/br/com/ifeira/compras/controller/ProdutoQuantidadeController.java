package br.com.ifeira.compras.controller;

import br.com.ifeira.compras.model.Produto;
import br.com.ifeira.compras.service.ProdutoQuantidadeService;
import br.com.ifeira.compras.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProdutoQuantidadeController {

    @Autowired
    private ProdutoQuantidadeService pqService;

//    @PostMapping("/guardarDados")
//    public void guardarDados(@ResponseBody List<Produto> produtos, List<Integer> qtds){
//
//    }
}
