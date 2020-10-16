package br.com.ifeira.compras.controller;

import br.com.ifeira.compras.model.Produto;
import br.com.ifeira.compras.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;

import java.util.Optional;


@RestController
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // ok
    @GetMapping("/listarProdutos")
    public ResponseEntity<?> listarProdutos() {
        try {
            return ResponseEntity.ok(produtoService.listarProduto());
        } catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
        }
    }

    @GetMapping("/buscarProduto")
    public ResponseEntity<?> buscarProduto(@QueryParam("cod_produto") String cod_produto){
        Long codProduto = Long.parseLong(cod_produto);
        Optional<Produto> optProduto = produtoService.selecionarProduto(codProduto);
        try {
            return ResponseEntity.ok(optProduto.get());
        } catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
        }
    }

    @PostMapping("/inserirProduto")
    public ResponseEntity<?> inserirProduto(@RequestBody Produto produto){
        produtoService.inserirProduto(produto);
        try {
            return ResponseEntity.ok("Produto inserido com sucesso");
        } catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
        }
    }


    @GetMapping("/deletarProduto")
    public ResponseEntity<?> deletarProduto(@QueryParam("cod_produto") String cod_produto){
        Long codProduto = Long.parseLong(cod_produto);
        produtoService.deletarProduto(codProduto);
        try {
            return ResponseEntity.ok("Excluido com sucesso");
        } catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
        }
    }
}
