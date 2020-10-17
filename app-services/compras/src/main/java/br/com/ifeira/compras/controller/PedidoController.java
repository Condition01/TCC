package br.com.ifeira.compras.controller;

import br.com.ifeira.compras.dao.UsuarioDAO;
import br.com.ifeira.compras.model.Pedido;
import br.com.ifeira.compras.model.ProdutoQuantidade;
import br.com.ifeira.compras.model.Reclamacao;
import br.com.ifeira.compras.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/criarPedido")
    public ResponseEntity<?> criarPedido(@RequestBody Pedido pedido) {
        try {
            return ResponseEntity.ok(pedidoService.criarPedido(pedido));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
        }
    }

    @RequestMapping("/listarPedidos")
    public ResponseEntity<?> listarPedidos() {
        try {
            List<Pedido> pedidos = pedidoService.listarPedidos();
            return ResponseEntity.ok(pedidos);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
        }
    }

    @RequestMapping("/acharPedido")
    public ResponseEntity<?> listarProdutos(@RequestParam("numero") Long numero) {
        try {
            return ResponseEntity.ok(pedidoService.acharPedido(numero));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
        }
    }




 /*
    @RequestMapping("/atualizarPedido")
    public Pedido upDatePedido(){
        return null;
    }



    @RequestMapping("/criarReclamacao")
    public boolean realizarReclamacao(){
        return false;
    }

    @RequestMapping("visualizarReclamacao")
    public Reclamacao visualizarReclamacao(){
        return null;
    }

    @RequestMapping("/atualizarReclamacao")
    public Reclamacao atualizarReclamacao(){
        return null;
    }

    @RequestMapping("/finalizarReclamacao")
    public Reclamacao finalizarReclamacao(){
        return null;
    }

    @RequestMapping("/pegarCarrinho")
    public List<ProdutoQuantidade> pegarCarrinho(){
        return null;
    }*/
}
