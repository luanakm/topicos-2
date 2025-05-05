package com.topicos.dois.service;

import com.topicos.dois.dto.request.TutorRequestDTO;
import com.topicos.dois.dto.response.TutorResponseDTO;
import com.topicos.dois.entity.Tutor;
import com.topicos.dois.repository.TutorRepository;
import com.topicos.dois.util.TutorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class TutorServiceImpl implements TutorService {

    private final TutorRepository tutorRepository;

    private final TutorMapper tutorMapper;


    @Override
    public TutorResponseDTO findById(Long id) {
     return tutorMapper.toTutorDTO(returnTutor(id));
    }

    @Override
    public List<TutorResponseDTO> findAll() {
        return tutorMapper.toTutorDTO(tutorRepository.findAll());
    }

    @Override
    public TutorResponseDTO register(TutorRequestDTO tutorRequestDTO) {

        Tutor tutor = tutorMapper.toTutor(tutorRequestDTO);

        return tutorMapper.toTutorDTO(tutorRepository.save(tutor));
    }

    @Override
    public TutorResponseDTO update(Long id, TutorRequestDTO tutorRequestDTO) {

        Tutor tutor = returnTutor(id);

        tutorMapper.atualizarTutor(tutor, tutorRequestDTO);

        return tutorMapper.toTutorDTO(tutorRepository.save(tutor));
    }

    @Override
    public String delete(Long id) {
        tutorRepository.deleteById(id);
        return "Tutor id: "+id+" deleted";
    }

    private Tutor returnTutor(Long id) {
       return tutorRepository.findById(id)
               .orElseThrow(()-> new RuntimeException("Tutor wasn't fount on database"));
    }

}
