package com.anbima.desafio_tecnico.repository;

import com.anbima.desafio_tecnico.model.Pedido;
import com.anbima.desafio_tecnico.model.Status;
import jakarta.transaction.Transactional;
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
    @Transactional
    @Query("UPDATE Pedido p set p.status = :status WHERE p.id = :id")
    void atualizarStatusDoPedido(Status status, Long id);
}
