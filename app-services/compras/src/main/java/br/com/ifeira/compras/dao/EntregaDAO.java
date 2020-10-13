package br.com.ifeira.compras.dao;

import br.com.ifeira.compras.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaDAO extends JpaRepository<Entrega, Long> {
}
