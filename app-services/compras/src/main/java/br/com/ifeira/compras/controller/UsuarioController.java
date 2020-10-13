package br.com.ifeira.compras.controller;

import br.com.ifeira.compras.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
}
