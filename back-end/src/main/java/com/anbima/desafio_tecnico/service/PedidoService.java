package com.anbima.desafio_tecnico.service;

import com.anbima.desafio_tecnico.model.Pedido;
import com.anbima.desafio_tecnico.model.Status;
import com.anbima.desafio_tecnico.repository.PedidoRepository;
import org.hibernate.stat.internal.StatsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;
    private List<Pedido> pedidos;

    public List<Pedido> listarPedidos(){
        pedidos = repository.findAllByOrderByIdAsc();

        return pedidos;
    }

    public void salvarPedido (Pedido p){
        try {
            repository.save(p);
            System.out.println("Pedido salvo no banco!");
        } catch (Exception e){
            System.out.println("ERRO: " + e.getMessage());
        }

    }

    public Pedido encontrarPedidoPorId(Long id){
        return repository.findById(id)
                .map(p -> new Pedido(p.getId(), p.getTipoLanche(), p.getProteina(),
                        p.getAcompanhamento(), p.getQuantidade(), p.getBebida(), p.getValor(), p.getStatus(), p.getCriadoEm()))
                .orElse(null);
    }

    public List<Pedido> encontrarPedidosPorStatus(String status){
        status = status.toUpperCase();

        Status statusEnun = Status.valueOf(status);
        System.out.println(statusEnun);

        try{
            pedidos = repository.findAllByStatus(statusEnun);
            return pedidos;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());

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
