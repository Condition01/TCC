package br.com.ifeira.compras.controller;

import br.com.ifeira.compras.model.Produto;
import br.com.ifeira.compras.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;

import java.util.Map;
import java.util.Optional;


@RestController
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PreAuthorize("hasAnyRole('ROLE_CLIENTE', 'ROLE_FEIRANTE','ROLE_ENTREGADOR')")
    @PostMapping("/getProdutos")
    public ResponseEntity<?> getProdutos(@RequestBody Map<Long,Integer> qtd_produto){
        try {
            return ResponseEntity.ok(produtoService.getListaProduto(qtd_produto));
        } catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_CLIENTE', 'ROLE_FEIRANTE','ROLE_ENTREGADOR')")
    @GetMapping("/listarProdutos")
    public ResponseEntity<?> listarProdutos() {
        try {
            return ResponseEntity.ok(produtoService.listarProduto());
        } catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_CLIENTE', 'ROLE_FEIRANTE','ROLE_ENTREGADOR')")
    @GetMapping("/listarProdutoQtd")
    public ResponseEntity<?> listarProdutoQtd() {
        try {
            return ResponseEntity.ok(produtoService.listarProdutoQtd());
        } catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_CLIENTE', 'ROLE_FEIRANTE','ROLE_ENTREGADOR')")
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
