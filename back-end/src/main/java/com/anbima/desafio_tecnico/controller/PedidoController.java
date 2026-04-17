package com.anbima.desafio_tecnico.controller;


import com.anbima.desafio_tecnico.model.Pedido;
import com.anbima.desafio_tecnico.service.PedidoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> getPedidos(){
        try{
            List<Pedido> pedidos = pedidoService.listarPedidos();
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            log.error("ERRO: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoPorId(@PathVariable Long id){

        try{
            Pedido p = pedidoService.encontrarPedidoPorId(id);

            if (p !=null){
                return ResponseEntity.ok(p);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("ERRO: {}",e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
    // rota temporaria para verificar os pedidos por status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Pedido>> buscarPorStatus(@PathVariable String status){
        try{
            List <Pedido> pedidoPorStatus = pedidoService.encontrarPedidosPorStatus(status);
            return ResponseEntity.ok(pedidoPorStatus);
        } catch (Exception e) {
            log.error("ERRO: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        }

    }
//rota de teste, substituir pela rota que recebe a string posicional
    @PostMapping
    public ResponseEntity<Pedido> salvarPedido(@RequestBody Pedido p){
        try{
            Pedido pedidoSalvo = pedidoService.salvarPedido(p);
            log.info("Pedido salvo no banco com sucesso! ID: {}", pedidoSalvo.getId());
            return ResponseEntity.status(201).body(pedidoSalvo);
        } catch (Exception e) {
            log.error("ERRO ao salvar o pedido: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        }

    }



}
