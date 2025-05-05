package com.topicos.dois.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_agenda")
@NoArgsConstructor
@Getter
@Setter
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "data inicio", nullable = false)
    private String datainicio;

    @Column(name = "datafim", nullable = false, unique = true)
    private String datafim;

    @Column(name = "tipo servico", nullable = false)
    private String tiposervico;

    @Column(name = "status", nullable = false)
    private String status;

    @Builder
    public Agenda(String datainicio, String datafim, String tiposervico, String status) {
        this.datainicio = datainicio;
        this.datafim = datafim;
        this.tiposervico = tiposervico;
        this.status = status;
    }

}
