package com.topicos.dois.service;


import com.topicos.dois.dto.request.TutorRequestDTO;
import com.topicos.dois.dto.response.TutorResponseDTO;

import java.util.List;

public interface TutorService {

    TutorResponseDTO findById(Long id);

    List<TutorResponseDTO> findAll();

    TutorResponseDTO register(TutorRequestDTO tutorRequestDTO);

    TutorResponseDTO update(Long id, TutorRequestDTO tutorRequestDTO);

    String delete(Long id);

}
