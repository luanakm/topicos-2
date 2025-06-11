package com.topicos.dois.dto.response;


import com.topicos.dois.entity.Agenda;
import com.topicos.dois.entity.Funcionario;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FuncionarioResponseDTO {

    private Long id;

    private String nome;

    private LocalDateTime dataCriacao;

    public FuncionarioResponseDTO(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.dataCriacao = funcionario.getDataCriacao();
    }


}
