package com.anbima.desafio_tecnico.service;

import com.anbima.desafio_tecnico.model.Pedido;
import com.anbima.desafio_tecnico.model.Status;
import com.anbima.desafio_tecnico.repository.PedidoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public List<Pedido> listarPedidos(){
        List<Pedido> pedidos = repository.findAllByOrderByIdAsc();

        return pedidos;
    }

    public Pedido salvarPedido (Pedido p){
        try {
            repository.save(p);
            log.info("Pedido salvo no banco!");
            return p;
        } catch (Exception e){
            log.error("Erro: {}", e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public Pedido encontrarPedidoPorId(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<Pedido> encontrarPedidosPorStatus(String status){
        status = status.toUpperCase();
        Status statusEnun = Status.valueOf(status);

        try{
            List<Pedido> pedidos = repository.findAllByStatus(statusEnun);
            return pedidos;

        } catch (Exception e) {
            log.error("Erro: " + e.getMessage());
            throw new RuntimeException(e.getMessage());

        }
    }

    public void atualizarPedido(String status, Long id){

        try{
            Optional<Pedido> p = repository.findById(id);
            Status novoStatus = Status.valueOf(status.toUpperCase());

            if (p.isPresent()){
                repository.atualizarStatusDoPedido(novoStatus, id);
              log.info("Pedido atualizado!");
            } else {
                log.info("Nenhum pedido encontrado.");
            }
        } catch(Exception e){

            log.error("Erro: " + e.getMessage());
        }


    }
}
