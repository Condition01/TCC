package br.com.ifeira.compra.boundary;

import br.com.ifeira.compra.controller.PedidoController;
import br.com.ifeira.compra.shared.entity.Carrinho;
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
@RequestMapping("/pedido")
public class PedidoBoundary {

    @Autowired
    private PedidoController pedidoController;
    private Logger logger = LoggerFactory.getLogger(PedidoBoundary.class);

    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    @PostMapping("/fechar")
    public ResponseEntity<?> fecharPedido(
            @RequestBody Carrinho carrinho, @RequestParam("cupom") String cupom, Principal principal) {
        try {
            return ResponseEntity.ok(this.pedidoController.fecharPedido(carrinho, cupom, principal));
        }catch (Exception e) {
            logger.error(e.getMessage());
            e.getStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    @GetMapping("/listar")
    public ResponseEntity<?> listarPedidos(Principal principal) {
        try {
            return ResponseEntity.ok(this.pedidoController.listarPedidos(principal));
        }catch (Exception e) {
            logger.error(e.getMessage());
            e.getStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    @PutMapping("/cancelar")
    public ResponseEntity<?> cancelarPedido(String pedCancReq) {
        try {
            JsonNode jsonObjResp = new ObjectMapper().readTree(pedCancReq);

            Long numeroPedido = jsonObjResp.get("idEntrega").asLong();

            return ResponseEntity.ok(this.pedidoController.cancelarPedido(numeroPedido));
        }catch (Exception e) {
            logger.error(e.getMessage());
            e.getStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
