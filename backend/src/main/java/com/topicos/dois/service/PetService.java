package com.topicos.dois.service;


import com.topicos.dois.dto.request.PetRequestDTO;
import com.topicos.dois.dto.request.TutorRequestDTO;
import com.topicos.dois.dto.response.PetResponseDTO;
import com.topicos.dois.dto.response.TutorResponseDTO;
import com.topicos.dois.entity.Pet;

import java.util.List;

public interface PetService {

    PetResponseDTO findById(Long id);

    List<PetResponseDTO> findAll();

    PetResponseDTO register(PetRequestDTO petRequestDTO);

    PetResponseDTO update(Long id, PetRequestDTO petRequestDTO);

    PetResponseDTO register(PetRequestDTO petRequestDTO);

    String delete(Long id);

}
