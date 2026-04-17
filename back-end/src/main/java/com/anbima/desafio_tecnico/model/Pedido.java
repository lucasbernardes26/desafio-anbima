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

    public Pedido(Optional<Pedido> p){
            this.id = p.get().getId();
            this.tipoLanche = p.get().getTipoLanche();
            this.proteina = p.get().getProteina();
            this.acompanhamento = p.get().getAcompanhamento();
            this.quantidade = p.get().getQuantidade();
            this.bebida = p.get().getBebida();
            this.valor = p.get().getValor();
            this.status = p.get().getStatus();
            this.criadoEm = p.get().getCriadoEm();

    }

    public Status setStatus(String status){
        Status newStatus = Status.valueOf(status.toUpperCase());
        return newStatus;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", tipoLanche='" + tipoLanche + '\'' +
                ", proteina='" + proteina + '\'' +
                ", acompanhamento='" + acompanhamento + '\'' +
                ", quantidade=" + quantidade +
                ", bebida='" + bebida + '\'' +
                ", valor=" + valor +
                ", status=" + status +
                ", criadoEm=" + criadoEm +
                "}\n";
    }
}
