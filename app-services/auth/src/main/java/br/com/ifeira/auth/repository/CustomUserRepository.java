package br.com.ifeira.auth.repository;

import br.com.ifeira.auth.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomUserRepository extends JpaRepository<String, CustomUser> { }
