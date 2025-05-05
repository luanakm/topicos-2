package com.topicos.dois.util;

import com.topicos.dois.dto.request.AgendaRequestDTO;
import com.topicos.dois.dto.request.TutorRequestDTO;
import com.topicos.dois.dto.response.AgendaResponseDTO;
import com.topicos.dois.dto.response.TutorResponseDTO;
import com.topicos.dois.entity.Agenda;
import com.topicos.dois.entity.Tutor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AgendaMapper {

    public Agenda toAgenda(AgendaRequestDTO agendaRequestDTO) {

        return Agenda.builder()
                .datainicio(agendaRequestDTO.getDatainicio())
                .datafim(agendaRequestDTO.getDatafim())
                .tiposervico(agendaRequestDTO.getTiposervico())
                .status(agendaRequestDTO.getStatus())
                .build();

    }

    public AgendaResponseDTO toAgendaDTO(Agenda agenda) {
        return new AgendaResponseDTO(agenda);
    }

    public List<AgendaResponseDTO> toAgendaDTO(List<Agenda> listaDeAgenda) {
        return listaDeAgenda.stream().map(AgendaResponseDTO::new).collect(Collectors.toList());
    }

    public void atualizarAgenda(Agenda agenda, AgendaRequestDTO agendaDTO) {

        agenda.setDatainicio(agendaDTO.getDatainicio());
        agenda.setDatafim(agendaDTO.getDatafim());
        agenda.setTiposervico(agendaDTO.getTiposervico());
        agenda.setStatus(agendaDTO.getStatus());

    }

}
