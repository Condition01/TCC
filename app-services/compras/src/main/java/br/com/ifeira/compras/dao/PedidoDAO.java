package br.com.ifeira.compras.dao;

import br.com.ifeira.compras.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoDAO extends JpaRepository<Pedido, Long> {

    @Query(value = "select * from tbl_pedido where numero = :numero", nativeQuery = true)
    public Optional<Pedido> acharPedido(@Param("numero") Long numero);
}
