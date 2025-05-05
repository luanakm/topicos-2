package com.topicos.dois.util;

import com.topicos.dois.dto.request.TutorRequestDTO;
import com.topicos.dois.dto.response.TutorResponseDTO;
import com.topicos.dois.entity.Tutor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TutorMapper {

    public Tutor toTutor(TutorRequestDTO tutorRequestDTO) {

        return Tutor.builder()
                .nome(tutorRequestDTO.getNome())
                .cpf(tutorRequestDTO.getCpf())
                .telefone(tutorRequestDTO.getTelefone())
                .endereco(tutorRequestDTO.getEndereco())
                .build();

    }

    public TutorResponseDTO toTutorDTO(Tutor tutor) {
        return new TutorResponseDTO(tutor);
    }

    public List<TutorResponseDTO> toTutorDTO(List<Tutor> listaDeTutores) {
        return listaDeTutores.stream().map(TutorResponseDTO::new).collect(Collectors.toList());
    }

    public void atualizarTutor(Tutor tutor, TutorRequestDTO tutorDTO) {

        tutor.setNome(tutorDTO.getNome());
        tutor.setCpf(tutorDTO.getCpf());
        tutor.setEndereco(tutorDTO.getEndereco());
        tutor.setTelefone(tutorDTO.getTelefone());

    }

}
