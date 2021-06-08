package br.com.ifeira.compra.boundary;

import br.com.ifeira.compra.controller.PagamentoController;
import br.com.ifeira.compra.entity.Pagamento;
import br.com.ifeira.compra.shared.exceptions.BusinessViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
            logger.info("Recebendo pagamento referente ao pedido: " + pagamento.getPedido().getNumeroPedido());
            return ResponseEntity.ok(this.pagamentoController.enfileirarPagamento(pagamento));
        }catch (Exception e) {
            logger.error(e.getMessage());
            if(e instanceof BusinessViolationException) {
                BusinessViolationException parsedException = (BusinessViolationException) e;
                return ResponseEntity.status(400).body(parsedException.getBusinessError());
            }
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    @GetMapping("/listar")
    public ResponseEntity<?> listarPagamentos(Principal principal) {
        try {
            logger.info("Listando pagamentos de: " + principal.getName());
            return ResponseEntity.ok(this.pagamentoController.listarPagamentos(principal));
        }catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
