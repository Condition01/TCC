package br.com.ifeira.compras.dao;

import br.com.ifeira.compras.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoDAO extends JpaRepository<Pagamento, Long> {
}
