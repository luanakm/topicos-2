package com.topicos.dois.service;


import com.topicos.dois.dto.request.AgendaRequestDTO;
import com.topicos.dois.dto.request.TutorRequestDTO;
import com.topicos.dois.dto.response.AgendaResponseDTO;
import com.topicos.dois.dto.response.TutorResponseDTO;

import java.util.List;

public interface AgendaService {

    AgendaResponseDTO findById(Long id);

    List<AgendaResponseDTO> findAll();

    AgendaResponseDTO register(AgendaRequestDTO agendaRequestDTO);

    AgendaResponseDTO update(Long id, AgendaRequestDTO agendaRequestDTO);

    String delete(Long id);

}
