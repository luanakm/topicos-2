package com.topicos.dois.dto.response;


import com.topicos.dois.entity.Agenda;
import lombok.Getter;

@Getter
public class AgendaResponseDTO {

    private Long id;

    private String datainicio;

    private String datafim ;

    private String tiposerviço;

    private String status;


    public AgendaResponseDTO(Agenda agenda) {
        this.id = agenda.getId();
        this.datainicio = agenda.getDatainicio();
        this.datafim = agenda.getDatafim();
        this.tiposerviço = agenda.getTiposervico();
        this.status = agenda.getStatus();
    }


}
