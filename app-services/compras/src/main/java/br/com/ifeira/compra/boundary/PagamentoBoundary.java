package br.com.ifeira.compra.boundary;

import br.com.ifeira.compra.entity.Pagamento;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamento")
public class PagamentoBoundary {

    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    @PostMapping("/enviar")
    public ResponseEntity<?> enviarPagamento(@RequestBody Pagamento pagamento) {
        return null;
    }

}
