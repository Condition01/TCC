package br.com.ifeira.auth.service;

import br.com.ifeira.auth.model.CustomUser;
import br.com.ifeira.auth.repository.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomUserRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CustomUser> usuarioOptional = this.usuarioRepository.findUsuarioByEmail(username);

        if(usuarioOptional.isPresent()){
            return usuarioOptional.get();
        }else {
            throw new UsernameNotFoundException("Usuário '%s' não encontrado: " + username);
        }

    }
}
