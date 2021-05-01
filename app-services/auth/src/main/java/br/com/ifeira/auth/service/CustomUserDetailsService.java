package br.com.ifeira.auth.service;

import br.com.ifeira.auth.entity.Pessoa;
import br.com.ifeira.auth.dao.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioDAO usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Pessoa> usuarioOptional = this.usuarioRepository.findUsuarioByEmail(username);

        if(usuarioOptional.isPresent()){
            return usuarioOptional.get();
        }else {
            throw new UsernameNotFoundException("Usuário '%s' não encontrado: " + username);
        }

    }
}
