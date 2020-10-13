package br.com.ifeira.compras.dao;

import br.com.ifeira.compras.model.Reclamacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamacaoDAO extends JpaRepository<Reclamacao, Long> {
}
