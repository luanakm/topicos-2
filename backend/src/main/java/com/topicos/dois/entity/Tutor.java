package com.topicos.dois.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_tutor")
@NoArgsConstructor
@Getter
@Setter
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "endereco", nullable = false)
    private String endereco;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Builder
    public Tutor(String nome, String cpf, String endereco, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
    }

}
