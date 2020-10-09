package br.com.ifeira.auth.repository;

import br.com.ifeira.auth.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {

    @Query(value = "select * from tbl_usuario where email = :email", nativeQuery = true)
    public Optional<CustomUser> findUsuarioByEmail(@Param("email") String email);

    @Query(value = "select * from tbl_usuario where cpf = :cpf", nativeQuery = true)
    public Optional<CustomUser> findUsuarioByCpf(@Param("cpf") String cpf);

}
