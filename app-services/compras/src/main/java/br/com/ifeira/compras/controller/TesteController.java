package br.com.ifeira.compras.controller;

import br.com.ifeira.compras.service.TesteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TesteController {

    @Autowired
    private TesteService testeService;

    @RequestMapping("/getClients")
    public ResponseEntity<?> getClients() {
        try {
            return ResponseEntity.ok(this.testeService.getClients());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
        }
    }

    @RequestMapping("/getPagamento")
    public ResponseEntity<?> getPagamento() {
        try {
            return ResponseEntity.ok(this.testeService.getPagamento());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
        }
    }
}
