package com.anbima.desafio_tecnico.repository;

import com.anbima.desafio_tecnico.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidosRepository extends JpaRepository<Pedido, Long> {
}
