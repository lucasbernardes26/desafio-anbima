package com.anbima.desafio_tecnico.core.repository;

import com.anbima.desafio_tecnico.core.model.Pedido;
import com.anbima.desafio_tecnico.core.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findAllByOrderByIdAsc();
    List<Pedido> findAllByStatus(Status status);

    @Modifying
    @Query("UPDATE Pedido p set p.status = :status WHERE p.id = :id")
    void atualizarStatusDoPedido(Status status, Long id);
}
