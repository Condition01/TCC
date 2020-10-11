package br.com.ifeira.auth.controller;

import br.com.ifeira.auth.model.Usuario;
import br.com.ifeira.auth.model.dtos.UsuarioDTO;
import br.com.ifeira.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('CLIENTE','FEIRANTE','ENTREGADOR')")
    @GetMapping
    public ResponseEntity<?> getUsuario() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Usuario usuario = (Usuario) principal;
            return ResponseEntity.ok(this.userService.pegarUsuarioCpf(usuario.getCpf()));
        }catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    @PreAuthorize("hasAnyRole('CLIENTE','FEIRANTE','ENTREGADOR')")
    @GetMapping("/userinfo")
    public ResponseEntity<?> userInfo() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Usuario usuario = (Usuario) principal;
            return ResponseEntity.ok(principal);
        }catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            return ResponseEntity.ok(this.userService.cadastroUsuario(usuarioDTO));
        }catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PreAuthorize("hasRole('CLIENTE')")
    @PutMapping
    public ResponseEntity<?> alterarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            return ResponseEntity.ok(this.userService.alterarUsuario(usuarioDTO));
        }catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('CLIENTE','FEIRANTE','ENTREGADOR')")
    @GetMapping("/email")
    public ResponseEntity<?> pegarUsuarioEmail(@RequestParam("email") String email) {
        try {
            return ResponseEntity.ok(this.userService.pegarUsuarioEmail(email));
        }catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
