package com.anbima.desafio_tecnico;

import com.anbima.desafio_tecnico.model.Pedido;
import com.anbima.desafio_tecnico.model.Status;
import com.anbima.desafio_tecnico.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class DesafioTecnicoApplicationTests {

    @Autowired
    private PedidoService pedidoService;

	@Test
	void contextLoads() {

// Testando inserção e atualização do status no banco de dados

       Pedido pedido1 = new Pedido(
                "X-Burger",
                "Carne Bovina",
                "Batata Frita",
                1,
                "Coca-Cola",
                BigDecimal.valueOf(35.50)
        );


        Pedido pedido2 = new Pedido(
                "X-Salada",
                "Frango Grelhado",
                "Anéis de Cebola",
                2,
                "Suco de Laranja",
                BigDecimal.valueOf(62.00)
        );


        Pedido pedido3 = new Pedido(
                "Hambúrguer",
                "Proteína de Soja",
                "Salada Especial",
                1,
                "Água Mineral",
                BigDecimal.valueOf(28.90)
        );

        Pedido pedido4 = new Pedido(
                "X-Tudo",
                "Picanha",
                "Nuggets",
                1,
                "Milkshake",
                BigDecimal.valueOf(45.00)
        );

        Pedido pedido5 = new Pedido(
                "Sanduíche Natural",
                "Peito de Peru",
                "Frutas Picadas",
                3,
                "Chá Gelado",
                BigDecimal.valueOf(54.30)
        );

        pedidoService.salvarPedido(pedido1);
        pedidoService.salvarPedido(pedido2);
        pedidoService.salvarPedido(pedido3);
        pedidoService.salvarPedido(pedido4);
        pedidoService.salvarPedido(pedido5);


        System.out.println("Listando todos os pedidos recém salvos no banco: ");
        List<Pedido> todosOsPedidos = pedidoService.listarPedidos();

        System.out.println(todosOsPedidos);

        pedidoService.atualizarPedido("recebido", todosOsPedidos.get(0).getId());
        pedidoService.atualizarPedido("recebido", todosOsPedidos.get(2).getId());

        System.out.println("Listando pedidos entregues: ");
        List<Pedido> pedidosEntregues =  pedidoService.encontrarPedidosPorStatus("entregue");

        System.out.println(pedidosEntregues);

	}

}
