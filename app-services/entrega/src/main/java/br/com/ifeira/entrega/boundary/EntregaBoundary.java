package br.com.ifeira.entrega.boundary;

import br.com.ifeira.compra.shared.exceptions.BusinessViolationException;
import br.com.ifeira.entrega.controller.EntregaController;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/entrega")
public class EntregaBoundary {

    @Autowired
    private EntregaController entregaController;

    private Logger logger = LoggerFactory.getLogger(EntregaBoundary.class);

    @PreAuthorize("hasRole('ROLE_ENTREGADOR')")
    @GetMapping("/listar")
    public ResponseEntity<?> listarEntregas(Principal principal, @RequestParam(name = "status", required = false) String status) {
        try {
            logger.info("Listando entregas de: " + principal.getName());
            return ResponseEntity.ok(this.entregaController.buscarEntregas(principal, status));
        }catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_ENTREGADOR')")
    @PostMapping("/realizar")
    public ResponseEntity<?> realizarEntrega(@RequestBody String idEntregaBody) {
        try {
            JsonNode jsonObjResp = new ObjectMapper().readTree(idEntregaBody);

            Long idEntrega = jsonObjResp.get("idEntrega").asLong();

            logger.info("Realizando entrega: " + idEntrega);
            return ResponseEntity.ok(this.entregaController.realizarEntrega(idEntrega));
        }catch (Exception e) {
            if(e instanceof BusinessViolationException) {
                BusinessViolationException parsedException = (BusinessViolationException) e;
                return ResponseEntity.status(400).body(parsedException.getBusinessError());
            }
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
