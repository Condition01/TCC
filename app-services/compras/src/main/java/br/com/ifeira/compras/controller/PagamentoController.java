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

    // ok
    @GetMapping("/listarPagamentos")
    public ResponseEntity<?> listarPagamentos(){
        try {
            return ResponseEntity.ok(pagamentoService.listarPagamentos());
        } catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
        }
    }

    //ok
    @GetMapping("/encontrarPagamento")
    public ResponseEntity<?> buscarPagamento(@QueryParam("numeroPagamento") String numeroPagamento){
        Long numero = Long.parseLong(numeroPagamento);
        Optional<Pagamento> optPagamento = pagamentoService.identificarPagamento(numero);
        try {
            return ResponseEntity.ok(optPagamento.get());
        } catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
        }
    }

    // ok
    @GetMapping("/deletarPagamento")
    public ResponseEntity<?> deletarPagamento(@QueryParam("numeroPagamento") String numeroPagamento){
        Long numero = Long.parseLong(numeroPagamento);
        try {
            return ResponseEntity.ok(pagamentoService.deletarPagamento(numero));
        } catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
        }
    }

    // bad request - erro de servidor 500, problemas com o relacionamento entre pagamento e pedido
    @PostMapping("/inserirPagamento")
    public ResponseEntity<?> inserirPagamento(@RequestBody Pagamento pagamento){
        pagamentoService.inserirPagamento(pagamento);
        try {
            return ResponseEntity.ok("Inserido com sucesso");
        } catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
        }
    }

}
