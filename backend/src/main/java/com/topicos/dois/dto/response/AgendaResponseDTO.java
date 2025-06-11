package com.topicos.dois.dto.response;


import com.topicos.dois.entity.Agenda;
import lombok.Getter;

@Getter
public class AgendaResponseDTO {

    private Long id;

    private String dataHora;

    private String nome;

    private String servico;

    private Long funcionarioId;

    private String funcionarioNome;

    public AgendaResponseDTO(Agenda agenda) {
        this.id = agenda.getId();
        this.dataHora = agenda.getDataHora();
        this.nome = agenda.getNome();
        this.servico = agenda.getServico();
        this.funcionarioId = agenda.getFuncionario().getId();
        this.funcionarioNome = agenda.getFuncionario().getNome();
    }


}
