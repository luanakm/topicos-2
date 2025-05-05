package com.topicos.dois.service;

import com.topicos.dois.dto.request.PetRequestDTO;
import com.topicos.dois.dto.response.PetResponseDTO;
import com.topicos.dois.entity.Pet;
import com.topicos.dois.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    private final PetMapper petMapper;


    @Override
    public PetResponseDTO findById(Long id) {
     return petMapper.toPetDTO(returnPet(id));
    }

    @Override
    public List<PetResponseDTO> findAll() {
        return petMapper.toPetDTO(petRepository.findAll());
    }

    @Override
    public PetResponseDTO register(PetRequestDTO petRequestDTO) {

        Pet pet = petMapper.toPet(petRequestDTO);

        return petMapper.toPetDTO(petRepository.save(pet));
    }

    @Override
    public PetResponseDTO update(Long id, PetRequestDTO petRequestDTO) {

        Pet pet = returnPet(id);

        petMapper.atualizarPet(pet, petRequestDTO);

        return petMapper.toMapperDTO(petRepository.save(pet));
    }

    @Override
    public String delete(Long id) {
        petRepository.deleteById(id);
        return "Pet id: "+id+" deleted";
    }

    private Pet returnPet(Long id) {
       return petRepository.findById(id)
               .orElseThrow(()-> new RuntimeException("Pet wasn't fount on database"));
    }

}
