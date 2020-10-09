package br.com.ifeira.auth.controller;

import br.com.ifeira.auth.model.CustomUser;
import br.com.ifeira.auth.model.Roles;
import br.com.ifeira.auth.model.Situacao;
import br.com.ifeira.auth.model.dtos.UsuarioDTO;
import br.com.ifeira.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Principal user(Principal user) {
        return user;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            return ResponseEntity.ok(this.userService.cadastroUsuario(usuarioDTO));
        }catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<?> pegarUsuarioCpf(@PathVariable("cpf") String cpf) {
        try {
            return ResponseEntity.ok(this.userService.pegarUsuarioCpf(cpf));
        }catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> pegarUsuarioEmail(@RequestParam("email") String email) {
        try {
            return ResponseEntity.ok(this.userService.pegarUsuarioEmail(email));
        }catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
