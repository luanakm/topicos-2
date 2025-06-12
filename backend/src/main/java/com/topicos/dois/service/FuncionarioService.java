package com.topicos.dois.service;

import com.topicos.dois.dto.request.FuncionarioRequestDTO;
import com.topicos.dois.dto.response.FuncionarioResponseDTO;
import com.topicos.dois.entity.Funcionario;

import java.util.List;

public interface FuncionarioService {

    Funcionario findById(Long id);

    List<FuncionarioResponseDTO> findByNome(String nome);

    List<FuncionarioResponseDTO> findAll();

    FuncionarioResponseDTO register(FuncionarioRequestDTO funcionarioRequestDTO);

    FuncionarioResponseDTO update(Long id, FuncionarioRequestDTO funcionarioRequestDTO);

    String delete(Long id);

}
