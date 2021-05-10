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
    public ResponseEntity<?> listarEntregas(Principal principal) {
        try {
            return ResponseEntity.ok(this.entregaController.buscarEntregas(principal));
        }catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_ENTREGADOR')")
    @PostMapping("/entregar")
    public ResponseEntity<?> listarEntregas(@RequestBody String reqEntregar) {
        try {
            JsonNode jsonObjResp = new ObjectMapper().readTree(reqEntregar);

            Long numeroEntrega = jsonObjResp.get("numeroEntrega").asLong();

            return ResponseEntity.ok(this.entregaController.realizarEntrega(numeroEntrega));
        }catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
