package com.topicos.dois.service;

import com.topicos.dois.dto.request.FuncionarioRequestDTO;
import com.topicos.dois.dto.response.FuncionarioResponseDTO;

import java.util.List;

public interface FuncionarioService {

    FuncionarioResponseDTO findById(Long id);

    List<FuncionarioResponseDTO> findByNome(String nome);

    List<FuncionarioResponseDTO> findAll();

    FuncionarioResponseDTO register(FuncionarioRequestDTO funcionarioRequestDTO);

    FuncionarioResponseDTO update(Long id, FuncionarioRequestDTO funcionarioRequestDTO);

    String delete(Long id);

}
