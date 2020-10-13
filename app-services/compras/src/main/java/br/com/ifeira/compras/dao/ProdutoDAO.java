package br.com.ifeira.compras.dao;
import br.com.ifeira.compras.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProdutoDAO extends JpaRepository<Produto, Long> {
}
