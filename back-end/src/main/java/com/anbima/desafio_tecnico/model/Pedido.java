package com.anbima.desafio_tecnico.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_lanche", length = 20)
    private String tipoLanche;

    @Column(length = 20)
    private String proteina;

    @Column(length = 20)
    private String acompanhamento;

    private Integer quantidade;

    @Column(length = 20)
    private String bebida;

    @Column(precision = 10, scale = 2)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status;

    @CreationTimestamp
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    public Pedido(){}

    public Pedido(String tipoLanche, String proteina, String acompanhamento, Integer quantidade, String bebida, BigDecimal valor) {
        this.tipoLanche = tipoLanche;
        this.proteina = proteina;
        this.acompanhamento = acompanhamento;
        this.quantidade = quantidade;
        this.bebida = bebida;
        this.valor = valor;
        this.status = Status.RECEBIDO;
    }

    public Long getId() {
        return id;
    }

    public String getTipoLanche() {
        return tipoLanche;
    }

    public String getProteina() {
        return proteina;
    }

    public String getAcompanhamento() {
        return acompanhamento;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getBebida() {
        return bebida;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }
}
