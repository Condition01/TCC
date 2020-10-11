package br.com.ifeira.auth.dao;

import br.com.ifeira.auth.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Long> {

    @Query(value = "select * from tbl_usuario where email = :email", nativeQuery = true)
    public Optional<Usuario> findUsuarioByEmail(@Param("email") String email);

    @Query(value = "select * from tbl_usuario where cpf = :cpf", nativeQuery = true)
    public Optional<Usuario> findUsuarioByCpf(@Param("cpf") String cpf);

    @Query(value = "select cpf from tbl_usuario where cpf = :cpf", nativeQuery = true)
    public Optional<String> findCpf(@Param("cpf") String cpf);

}
