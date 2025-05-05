package com.topicos.dois.dto.response;


import com.topicos.dois.entity.Tutor;
import lombok.Getter;

@Getter
public class TutorResponseDTO {

    private Long id;

    private String nome;

    private String cpf;

    private String endereco;

    private String telefone;


    public TutorResponseDTO(Tutor person) {
        this.id = person.getId();
        this.nome = person.getNome();
        this.cpf = person.getCpf();
        this.endereco = person.getEndereco();
        this.telefone = person.getTelefone();
    }


}
