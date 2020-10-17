package br.com.ifeira.compras.controller;

import br.com.ifeira.compras.model.Pagamento;
import br.com.ifeira.compras.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.Optional;

@RestController
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping("/listarPagamentos")
    public ResponseEntity<?> listarPagamentos() {
        try {
            return ResponseEntity.ok(this.pagamentoService.listarPagamentos());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
        }
    }

    @GetMapping("/encontrarPagamento")
    public ResponseEntity<?>
    buscarPagamento(@QueryParam("numeroPagamento") String numeroPagamento) {
        Long numero = Long.parseLong(numeroPagamento);
        Optional<Pagamento> optPagamento = this.pagamentoService.identificarPagamento(numero);
        try {
            return ResponseEntity.ok(optPagamento.get());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
        }
    }

    @GetMapping("/deletarPagamento")
    public ResponseEntity<?> deletarPagamento(@QueryParam("numeroPagamento") String numeroPagamento) {
        Long numero = Long.parseLong(numeroPagamento);
        try {
            return ResponseEntity.ok(this.pagamentoService.deletarPagamento(numero));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
        }
    }
}
