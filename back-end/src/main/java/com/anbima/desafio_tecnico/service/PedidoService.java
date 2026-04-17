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

    public void salvarPedido (Pedido p){
        try {
            repository.save(p);
            log.info("Pedido salvo no banco com sucesso!");
        } catch (Exception e){
            log.error("Erro: {}", e.getMessage());
        }

    }

    public Pedido encontrarPedidoPorId(Long id){
        return repository.findById(id).orElse(null);
              //  .map(p -> new Pedido(p.getId(), p.getTipoLanche(), p.getProteina(),
               //         p.getAcompanhamento(), p.getQuantidade(), p.getBebida(), p.getValor(), p.getStatus(), p.getCriadoEm()))
               // ;
    }

    public List<Pedido> encontrarPedidosPorStatus(String status){
        status = status.toUpperCase();
        Status statusEnun = Status.valueOf(status);

        try{
            List<Pedido> pedidos = repository.findAllByStatus(statusEnun);
            return pedidos;

        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());

        } catch (Exception e){
            log.error("Erro: {}", e.getMessage());
            return null;
        }
    }

    public void atualizarPedido(String status, Long id){

        try{
            Optional<Pedido> p = repository.findById(id);
            Status novoStatus = Status.valueOf(status.toUpperCase());

            if (p.isPresent()){
                System.out.println();
                repository.atualizarStatusDoPedido(novoStatus, id);
            } else {
                // implementar resposta json caso o pedido não seja encontrado
                System.out.println("Pedido não encontrado, não foi possível atualizar o status");
            }
        } catch(Exception e){
            // retornar resposta json na versão web
            System.out.println("Erro: " + e.getMessage());
        }


    }
}
