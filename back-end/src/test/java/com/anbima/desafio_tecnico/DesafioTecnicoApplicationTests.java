package com.anbima.desafio_tecnico;

import com.anbima.desafio_tecnico.modulo_entrada.PedidoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesafioTecnicoApplicationTests {

    @Autowired
    private PedidoService pedidoService;

	@Test
	void contextLoads() {
	}

}
