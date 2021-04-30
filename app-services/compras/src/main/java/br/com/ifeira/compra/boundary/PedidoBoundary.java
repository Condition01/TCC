package br.com.ifeira.compra.boundary;

import br.com.ifeira.compra.controller.PedidoController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoBoundary {

    @Autowired
    private PedidoController pedidoController;
    private Logger logger = LoggerFactory.getLogger(PedidoBoundary.class);



}
