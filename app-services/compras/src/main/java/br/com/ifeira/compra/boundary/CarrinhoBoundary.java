package br.com.ifeira.compra.boundary;

import br.com.ifeira.compra.controller.CarrinhoController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoBoundary {

    @Autowired
    private CarrinhoController carrinhoController;
    private Logger logger = LoggerFactory.getLogger(CarrinhoBoundary.class);

    @PostMapping("/{contexto}/recuperar")
    public ResponseEntity<?> recuperarCarrinho(@RequestBody Map<String, Integer> prodQtdMap, @PathVariable("contexto") String contexto) {
        try {
            return ResponseEntity.ok(this.carrinhoController.recuperarCarrinho(prodQtdMap, contexto));
        }catch (Exception e) {
            logger.error(e.getMessage());
            e.getStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
