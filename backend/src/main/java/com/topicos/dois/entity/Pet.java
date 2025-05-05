package com.topicos.dois.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_pet")
@NoArgsConstructor
@Getter
@Setter
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    //@Column(name = "cod tutor", nullable = false, unique = true)
    //private String codtutor;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "sexo", nullable = false)
    private String sexo;

    @Builder
    public Pet(String nome, String tipo, String sexo) {
        this.nome = nome;
        this.tipo = tipo;
        this.sexo = sexo;
    }

}
