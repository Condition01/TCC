package br.com.ifeira.auth.boundary;

import br.com.ifeira.auth.entity.Pessoa;
import br.com.ifeira.auth.dtos.PessoaDTO;
import br.com.ifeira.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserBoundary {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_CLIENTE','ROLE_FEIRANTE','ROLE_ENTREGADOR')")
    @GetMapping
    public ResponseEntity<?> getUsuario() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Pessoa pessoa = (Pessoa) principal;
            return ResponseEntity.ok(this.userService.pegarUsuarioCpf(pessoa.getCpf()));
        }catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_CLIENTE','ROLE_FEIRANTE','ROLE_ENTREGADOR')")
    @GetMapping("/userinfo")
    public ResponseEntity<?> userInfo() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Pessoa pessoa = (Pessoa) principal;
            return ResponseEntity.ok(principal);
        }catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody PessoaDTO pessoaDTO) {
        try {
            return ResponseEntity.ok(this.userService.cadastroUsuario(pessoaDTO));
        }catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    @PutMapping
    public ResponseEntity<?> alterarUsuario(@RequestBody PessoaDTO pessoaDTO) {
        try {
            return ResponseEntity.ok(this.userService.alterarUsuario(pessoaDTO));
        }catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_CLIENTE','ROLE_FEIRANTE','ROLE_ENTREGADOR')")
    @GetMapping("/email")
    public ResponseEntity<?> pegarUsuarioEmail(@RequestParam("email") String email) {
        try {
            return ResponseEntity.ok(this.userService.pegarUsuarioEmail(email));
        }catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
