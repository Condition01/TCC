package br.com.ifeira.compras.dao;

import br.com.ifeira.compras.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, String> {
}
