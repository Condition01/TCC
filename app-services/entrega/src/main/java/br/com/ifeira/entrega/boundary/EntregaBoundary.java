package br.com.ifeira.entrega.boundary;

import br.com.ifeira.entrega.controller.EntregaController;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @PreAuthorize("hasRole('ROLE_ENTREGADOR')")
    @GetMapping("/listar")
    public ResponseEntity<?> listarEntregas(Principal principal, @RequestParam(name = "status", required = false) String status) {
        try {
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

            return ResponseEntity.ok(this.entregaController.realizarEntrega(idEntrega));
        }catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
