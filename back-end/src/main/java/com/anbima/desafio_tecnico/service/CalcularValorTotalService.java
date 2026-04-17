package com.anbima.desafio_tecnico.service;

import com.anbima.desafio_tecnico.model.Pedido;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalcularValorTotalService {

    public BigDecimal calcularValorTotal(Pedido p){
        double valor;
        double valorTotal;
        //condição para verificar se o pedido tem 10% de desconto

        boolean condicaoDesconto10 = p.getTipoLanche().equalsIgnoreCase("HAMBURGUER")
                && p.getProteina().equalsIgnoreCase("CARNE")
                && p.getAcompanhamento().equalsIgnoreCase("SALADA");

        if (condicaoDesconto10){
            valor = 20.00;
            valorTotal = (valor * p.getQuantidade())*0.9;
        } else if (p.getTipoLanche().equalsIgnoreCase("HAMBURGUER")) {
            valor = 20.00;
            valorTotal = valor * p.getQuantidade();
        } else if (p.getTipoLanche().equalsIgnoreCase("pastel")) {
            valor = 15.00;
            valorTotal = valor * p.getQuantidade();
        } else {
            valor =12.00;
            valorTotal = valor * p.getQuantidade();
        }

        return BigDecimal.valueOf(valorTotal);
    }

}

