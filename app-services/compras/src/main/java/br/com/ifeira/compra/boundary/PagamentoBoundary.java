package br.com.ifeira.compra.boundary;

import br.com.ifeira.compra.controller.PagamentoController;
import br.com.ifeira.compra.entity.Pagamento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamento")
public class PagamentoBoundary {

    @Autowired
    private PagamentoController pagamentoController;
    private Logger logger = LoggerFactory.getLogger(PagamentoBoundary.class);

    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    @PostMapping("/enviar")
    public ResponseEntity<?> enviarPagamento(@RequestBody Pagamento pagamento) {
        try {
            return ResponseEntity.ok(this.pagamentoController.enfileirarPagamento(pagamento));
        }catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
