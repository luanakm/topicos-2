package com.topicos.dois.controller;

import com.topicos.dois.dto.request.FuncionarioRequestDTO;
import com.topicos.dois.dto.response.FuncionarioResponseDTO;
import com.topicos.dois.service.AgendaService;
import com.topicos.dois.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/funcionario")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity<List<FuncionarioResponseDTO>> findByNome(@PathVariable(name = "nome") String nome) {
        return ResponseEntity.ok().body(funcionarioService.findByNome(nome));
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDTO>> findAll() {
        return ResponseEntity.ok().body(funcionarioService.findAll());
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> register(@RequestBody FuncionarioRequestDTO funcionarioRequestDTO, UriComponentsBuilder uriBuilder) {

        FuncionarioResponseDTO FuncionarioResponseDTO = funcionarioService.register(funcionarioRequestDTO);

        URI uri = uriBuilder.path("/funcionario/{id}").buildAndExpand(FuncionarioResponseDTO.getId()).toUri();

      return ResponseEntity.created(uri).body(FuncionarioResponseDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FuncionarioResponseDTO> update(@RequestBody FuncionarioRequestDTO funcionarioDTO, @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(funcionarioService.update(id,funcionarioDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(funcionarioService.delete(id));
    }

}
