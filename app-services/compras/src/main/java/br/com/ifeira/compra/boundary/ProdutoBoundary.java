package br.com.ifeira.compra.boundary;

import br.com.ifeira.compra.controller.ProdutoController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
public class ProdutoBoundary {

    @Autowired
    private ProdutoController produtoController;
    private Logger logger = LoggerFactory.getLogger(ProdutoBoundary.class);

    @GetMapping("/{contexto}/listar")
    public ResponseEntity<?> listarFLV(@PathVariable("contexto") String contexto) {
        try {
            return ResponseEntity.ok(this.produtoController.listarFLV(contexto));
        }catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/{contexto}/buscarFLVS")
    public ResponseEntity<?> buscarFLVS(@RequestParam("nome") String nome, @PathVariable("contexto") String contexto) {
        try {
            return ResponseEntity.ok(this.produtoController.buscarFLVS(nome, contexto));
        }catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
