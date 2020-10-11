package br.com.ifeira.auth.service;

import br.com.ifeira.auth.model.Usuario;
import br.com.ifeira.auth.enums.Roles;
import br.com.ifeira.auth.enums.Situacao;
import br.com.ifeira.auth.model.dtos.UsuarioDTO;
import br.com.ifeira.auth.dao.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioDAO usuarioDAO;

    public UsuarioDTO cadastroUsuario(UsuarioDTO usuarioDTO) {
        if(usuarioExiste(usuarioDTO.getCpf())){
            throw new DuplicateKeyException("Usuário já existe!!");
        }else {
            Usuario usuario = Usuario.fromDTO(usuarioDTO);
            usuario.getEndereco().setUsuario(usuario);
            usuario.setRole(Roles.CLIENTE);
            usuario.setSitucao(Situacao.HABILITADO);
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            return UsuarioDTO.fromUsuario(usuarioDAO.save(usuario));
        }
    }

    public UsuarioDTO alterarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuarioAlterado = Usuario.fromDTO(usuarioDTO);
        Optional<Usuario> optUsuario = this.usuarioDAO.findUsuarioByCpf(usuarioAlterado.getCpf());
        if(optUsuario.isPresent()){
            Usuario usuario = optUsuario.get();
            usuario.setEmail(usuarioAlterado.getEmail());
            usuario.setDataNasc(usuarioAlterado.getDataNasc());
            usuario.setNome(usuarioAlterado.getNome());
            usuario.setSobrenome(usuarioAlterado.getSobrenome());
            return UsuarioDTO.fromUsuario(usuarioDAO.save(usuario));
        }else{
            throw new UsernameNotFoundException("Não se pode alterar um usuario não presente");
        }
    }

    public UsuarioDTO pegarUsuarioCpf(String cpf) {
        Optional<Usuario> userOp = usuarioDAO.findUsuarioByCpf(cpf);
        if(userOp.isPresent()){
            return UsuarioDTO.fromUsuario(userOp.get());
        }else{
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
    }

    public UsuarioDTO pegarUsuarioEmail(String email) {
        Optional<Usuario> userOp = usuarioDAO.findUsuarioByEmail(email);
        if(userOp.isPresent()){
            return UsuarioDTO.fromUsuario(userOp.get());
        }else{
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
    }

    public boolean usuarioExiste(String cpf) {
        Optional<String> cpfOption = this.usuarioDAO.findCpf(cpf);
        return cpfOption.isPresent();
    }
}
