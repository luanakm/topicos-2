package com.topicos.dois.util;

import com.topicos.dois.dto.request.PetRequestDTO;
import com.topicos.dois.dto.request.TutorRequestDTO;
import com.topicos.dois.dto.response.PetResponseDTO;
import com.topicos.dois.dto.response.TutorResponseDTO;
import com.topicos.dois.entity.Pet;
import com.topicos.dois.entity.Tutor;
import com.topicos.dois.service.PetService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PetMapper {

    public Pet toPet(PetRequestDTO petRequestDTO) {

        return Pet.builder()
                .nome(petRequestDTO.getNome())
                .tipo(petRequestDTO.getTipo())
                .sexo(petRequestDTO.getSexo())
                .build();

    }

    public PetResponseDTO topetDTO(Pet pet) {
        return new PetResponseDTO(pet);
    }

    public List<PetResponseDTO> toPetDTO(List<Pet> listaDePet) {
        return listaDePet.stream().map(PetResponseDTO::new).collect(Collectors.toList());
    }

    public void atualizarPet(Pet pet, PetRequestDTO petDTO) {

        pet.setNome(petDTO.getNome());
        pet.setTipo(petDTO.getTipo());
        pet.setSexo(petDTO.getSexo());

    }

}
