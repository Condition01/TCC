package br.com.ifeira.compras.dao;

import br.com.ifeira.compras.model.ProdutoQuantidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoQuantidadeDAO extends JpaRepository<ProdutoQuantidade,Long> {
}
