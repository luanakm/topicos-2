package com.topicos.dois.dto.response;


import com.topicos.dois.entity.Pet;
import com.topicos.dois.entity.Tutor;
import lombok.Getter;

@Getter
public class PetResponseDTO {

    private Long id;

    private String nome;

    private String tipo;

    private String sexo;



    public PetResponseDTO(Pet pet) {
        this.id = pet.getId();
        this.nome = pet.getNome();
        this.tipo = pet.getTipo();
        this.sexo = pet.getSexo();
    }


}
