package br.com.ifeira.compra.boundary;

import br.com.ifeira.compra.controller.PedidoController;
import br.com.ifeira.compra.shared.entity.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/pedido")
public class PedidoBoundary {

    @Autowired
    private PedidoController pedidoController;
    private Logger logger = LoggerFactory.getLogger(PedidoBoundary.class);

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    @RequestMapping("/fechar")
    public ResponseEntity<?> fecharPedido(@RequestBody Pedido pedido, Principal principal) {
        try {
            System.out.println(principal.getName());
            return ResponseEntity.ok(this.pedidoController.fecharPedido(pedido, principal));
        }catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
