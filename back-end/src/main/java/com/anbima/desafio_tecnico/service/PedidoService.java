package com.anbima.desafio_tecnico.service;

import com.anbima.desafio_tecnico.model.Pedido;
import com.anbima.desafio_tecnico.model.Status;
import com.anbima.desafio_tecnico.repository.PedidoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private CalcularValorTotalService calcularValorTotalService;

    public List<Pedido> listarPedidos(){
        return repository.findAllByOrderByIdAsc();
    }

    public Pedido salvarPedido (Pedido p){
        try {
            repository.save(p);
            log.info("Pedido salvo no banco com sucesso!");

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
            List<Pedido> pedidos;
            pedidos = repository.findAllByStatus(statusEnun);
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
                repository.atualizarStatusDoPedido(novoStatus, id);
            } else {
                log.info("Não existe nenhum pedido com esse ID");
            }
        } catch(Exception e){
            log.error("Erro: " + e.getMessage());

        }
    }



    public Pedido processarStringPosicional(String linha) {
        if (linha == null || linha.length() != 40) {
            throw new IllegalArgumentException("A linha posicional deve ter exatos 40 caracteres.");
        }

        String strLanche = linha.substring(0, 10);
        String strProteina = linha.substring(10, 20);
        String strAcompanhamento = linha.substring(20, 30);
        String strQuantidade = linha.substring(30, 32);
        String strBebida = linha.substring(32, 40);

        String tipoLanche = strLanche.trim();
        String proteina = strProteina.trim();
        String acompanhamento = strAcompanhamento.trim();
        String bebida = strBebida.trim();
        Integer quantidade = Integer.parseInt(strQuantidade);
        BigDecimal valorCalculado;

        if (quantidade < 1 || quantidade > 99) {
            throw new IllegalArgumentException("A quantidade deve estar entre 01 e 99.");
        }

        Pedido novoPedido = new Pedido(tipoLanche, proteina, acompanhamento, quantidade, bebida);
        valorCalculado = calcularValorTotalService.calcularValorTotal(novoPedido);
        novoPedido.setValor(valorCalculado);

        return salvarPedido(novoPedido);
    }
}
