package br.com.ifeira.auth.service;

import br.com.ifeira.auth.model.CustomUser;
import br.com.ifeira.auth.model.Roles;
import br.com.ifeira.auth.model.Situacao;
import br.com.ifeira.auth.model.dtos.UsuarioDTO;
import br.com.ifeira.auth.repository.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserRepository customUserRepository;

    public UsuarioDTO cadastroUsuario(UsuarioDTO usuarioDTO) {
        CustomUser usuario = CustomUser.fromDTO(usuarioDTO);
        usuario.addRoles(Roles.ROLE_USER);
        usuario.setSitucao(Situacao.HABILITADO);
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return UsuarioDTO.fromCustomUser(customUserRepository.save(usuario));
    }

    public UsuarioDTO pegarUsuarioCpf(String cpf) {
        Optional<CustomUser> userOp = customUserRepository.findUsuarioByCpf(cpf);
        if(userOp.isPresent()){
            return UsuarioDTO.fromCustomUser(userOp.get());
        }else{
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
    }

    public UsuarioDTO pegarUsuarioEmail(String email) {
        Optional<CustomUser> userOp = customUserRepository.findUsuarioByEmail(email);
        if(userOp.isPresent()){
            return UsuarioDTO.fromCustomUser(userOp.get());
        }else{
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
    }
}
