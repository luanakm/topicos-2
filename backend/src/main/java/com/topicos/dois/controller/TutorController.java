package com.topicos.dois.controller;

import com.topicos.dois.dto.request.TutorRequestDTO;
import com.topicos.dois.dto.response.TutorResponseDTO;
import com.topicos.dois.service.TutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/tutor")
@RequiredArgsConstructor
public class TutorController {

    private final TutorService tutorService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TutorResponseDTO> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(tutorService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TutorResponseDTO>> findAll() {
        return ResponseEntity.ok().body(tutorService.findAll());
    }

    @PostMapping
    public ResponseEntity<TutorResponseDTO> register(@RequestBody TutorRequestDTO personRequestDTO, UriComponentsBuilder uriBuilder) {

        TutorResponseDTO tutorResponseDTO = tutorService.register(personRequestDTO);

        URI uri = uriBuilder.path("/people/{id}").buildAndExpand(tutorResponseDTO.getId()).toUri();

      return ResponseEntity.created(uri).body(tutorResponseDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TutorResponseDTO> update(@RequestBody TutorRequestDTO personDTO, @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(tutorService.update(id,personDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(tutorService.delete(id));
    }

}
