package br.com.ifeira.auth.dao;

import br.com.ifeira.auth.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioDAO extends JpaRepository<Pessoa, Long> {

    @Query(value = "select * from PESSOA p where p.EMAIL = :email", nativeQuery = true)
    public Optional<Pessoa> findUsuarioByEmail(@Param("email") String email);

    @Query(value = "select * from PESSOA p where p.CPF = :cpf", nativeQuery = true)
    public Optional<Pessoa> findUsuarioByCpf(@Param("cpf") String cpf);

    @Query(value = "select CPF from PESSOA p where p.CPF = :cpf", nativeQuery = true)
    public Optional<String> findCpf(@Param("cpf") String cpf);

}
