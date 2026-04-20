package com.anbima.desafio_tecnico.modulo_entrada;


import com.anbima.desafio_tecnico.core.model.Pedido;
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

    @PostMapping(value = "/posicional", consumes = "text/plain")
    public ResponseEntity<Pedido> criarPorPosicional(@RequestBody String linhaPosicional) {

        try {
            Pedido pedidoSalvo = pedidoService.processarStringPosicional(linhaPosicional);
            return ResponseEntity.status(201).body(pedidoSalvo);

        } catch (IllegalArgumentException e) {
            log.error("Erro de validação posicional: {}", e.getMessage());

            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("Erro interno ao processar posicional: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }



}
