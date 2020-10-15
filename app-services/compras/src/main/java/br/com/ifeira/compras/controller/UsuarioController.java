package br.com.ifeira.compras.controller;

import br.com.ifeira.compras.model.Usuario;
import br.com.ifeira.compras.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    //ok
    @GetMapping("/listarUsuario")
    public List<Usuario> listarUsuario()
    {
        return usuarioService.listarUsuario();
    }

    //não funcionando ainda
    @PostMapping ("/listarUsuarioPorCpf")
    public String buscarUsuario(@RequestBody String cpf){
        Optional<Usuario> optUser = usuarioService.buscarUsuario(cpf);
        if(optUser.isPresent()){
            return "deu ruim";
        }
        return "deu bom";
    }

    // ok
    @PostMapping("/inserirUsuario")
    public String inserirUsuario(@RequestBody Usuario usuario){
        try {
            usuarioService.inserirUsuario(usuario);
            return "Usuario Inserido com sucesso";
        } catch (Exception ex){
            String retorno = "Usuário não inserido - Mensagem de Erro" + ex.toString();
            return retorno;
        }
    }

    //ok
    @PostMapping("/deletarUsuario")
    public String deleteUsuario(@RequestBody Usuario usuario){
        return usuarioService.deletarUsuario(usuario);
    }

    // ok
    @PostMapping("/editarUsuario")
    public Usuario editarUsuario(@RequestBody Usuario usuario){
        Optional<Usuario> optUser = usuarioService.editarUsuario(usuario);
        if(optUser.isPresent()){
            return optUser.get();
        }
        return null;
    }
}
