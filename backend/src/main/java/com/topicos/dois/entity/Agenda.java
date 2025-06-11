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

    @Column(name = "data hora", nullable = false)
    private String dataHora;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "servico", nullable = false)
    private String servico;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @Builder
    public Agenda(String dataHora, String nome, String servico, Funcionario funcionario) {
        this.dataHora = dataHora;
        this.nome = nome;
        this.servico = servico;
        this.funcionario = funcionario;
    }

}
