package br.com.ifeira.compra.boundary;

import br.com.ifeira.compra.controller.FeiraController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feira")
public class FeiraBoundary {

    @Autowired
    private FeiraController feiraController;
    private Logger logger = LoggerFactory.getLogger(FeiraBoundary.class);

    @GetMapping("/listar")
    public ResponseEntity<?> listarFeiras() {
        try {
            logger.info("Listando feiras...");
            return ResponseEntity.ok(this.feiraController.listarFeiras());
        }catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> selecionarFeira(@RequestParam("feira") String feira) {
        try {
            logger.info("Buscando feiras com prefixo: " + feira);
            return ResponseEntity.ok(this.feiraController.selecionarFeira(feira));
        }catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
