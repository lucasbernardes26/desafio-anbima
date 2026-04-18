package com.anbima.desafio_tecnico.listener;

import com.anbima.desafio_tecnico.service.PedidoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;


@Slf4j
@Component
public class PedidoListener {

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "pedidos.recebidos")
    public void consumirPedido(String mensagemJson){

        //transforma a string em JSON, pega valor da chave e guarda na variável
        JsonNode jsonNode = objectMapper.readTree(mensagemJson);
        Long idRecebido = jsonNode.get("pedidoId").asLong();

        log.info("Teste. ID redebido: {}", idRecebido);

        try{
            //10s pra consumir e atualizar o pedido no bd
            Thread.sleep(10000);

            pedidoService.atualizarPedido("entregue", idRecebido);
            log.info("Pedido {} entregue!", idRecebido);
        } catch (Exception e){
            log.error("Erro no processamento da fila {}", e.getMessage());
        }
    }
}