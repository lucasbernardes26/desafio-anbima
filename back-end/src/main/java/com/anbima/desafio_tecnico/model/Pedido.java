package com.anbima.desafio_tecnico.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name="pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private Status status = Status.RECEBIDO;

    @CreationTimestamp
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    public Pedido(String tipoLanche, String proteina, String acompanhamento, Integer quantidade, String bebida) {
        this.tipoLanche = tipoLanche;
        this.proteina = proteina;
        this.acompanhamento = acompanhamento;
        this.quantidade = quantidade;
        this.bebida = bebida;
    }

}
