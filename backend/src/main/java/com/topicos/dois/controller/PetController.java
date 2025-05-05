package com.topicos.dois.controller;

import com.topicos.dois.dto.request.PetRequestDTO;
import com.topicos.dois.dto.request.TutorRequestDTO;
import com.topicos.dois.dto.response.PetResponseDTO;
import com.topicos.dois.dto.response.TutorResponseDTO;
import com.topicos.dois.service.PetService;
import com.topicos.dois.service.TutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/pet")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PetResponseDTO> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(petService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PetResponseDTO>> findAll() {
        return ResponseEntity.ok().body(petService.findAll());
    }

    @PostMapping
    public ResponseEntity<PetResponseDTO> register(@RequestBody PetRequestDTO petRequestDTO, UriComponentsBuilder uriBuilder) {

        PetResponseDTO petResponseDTO = petService.register(petRequestDTO);

        URI uri = uriBuilder.path("/pet/{id}").buildAndExpand(petResponseDTO.getId()).toUri();

      return ResponseEntity.created(uri).body(petResponseDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PetResponseDTO> update(@RequestBody PetRequestDTO petDTO, @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(petService.update(id,petDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(petService.delete(id));
    }

}
