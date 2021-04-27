package br.com.ifeira.compra.boundary;

import br.com.ifeira.compra.controller.ProdutoController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
public class ProdutoBoundary {

    @Autowired
    private ProdutoController produtoController;
    private Logger logger = LoggerFactory.getLogger(ProdutoBoundary.class);

    @GetMapping("/listar")
    public ResponseEntity<?> listarFLV() {
        try {
            return ResponseEntity.ok(this.produtoController.listarFLV());
        }catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/buscarFLV")
    public ResponseEntity<?> buscarFLV(@RequestParam("nome") String nome) {
        try {
            return ResponseEntity.ok(this.produtoController.buscarFLV(nome));
        }catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
