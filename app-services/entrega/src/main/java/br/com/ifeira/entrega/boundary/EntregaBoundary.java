package br.com.ifeira.entrega.boundary;

import br.com.ifeira.entrega.controller.EntregaController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entrega")
public class EntregaBoundary {

    @Autowired
    private EntregaController entregaController;

}
