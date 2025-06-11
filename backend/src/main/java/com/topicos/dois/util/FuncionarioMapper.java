package com.topicos.dois.util;

import com.topicos.dois.dto.request.AgendaRequestDTO;
import com.topicos.dois.dto.request.FuncionarioRequestDTO;
import com.topicos.dois.dto.response.FuncionarioResponseDTO;
import com.topicos.dois.entity.Funcionario;
import com.topicos.dois.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FuncionarioMapper {

    public Funcionario toFuncionario(FuncionarioRequestDTO funcionarioRequestDto) {
        return Funcionario.builder()
                .nome(funcionarioRequestDto.getNome())
                .build();
    }

    public FuncionarioResponseDTO toFuncionarioDTO(Funcionario funcionario) {
        return new FuncionarioResponseDTO(funcionario);
    }

    public List<FuncionarioResponseDTO> toFuncionariosDTO(List<Funcionario> funcionarios) {
        return funcionarios.stream().map(FuncionarioResponseDTO::new).collect(Collectors.toList());
    }

    public List<FuncionarioResponseDTO> toFuncionarioDTO(List<Funcionario> listaDeFuncionario) {
        return listaDeFuncionario.stream().map(FuncionarioResponseDTO::new).collect(Collectors.toList());
    }

    public void atualizarFuncionario(Funcionario funcionario, FuncionarioRequestDTO funcionarioDTO) {
        funcionario.setNome(funcionarioDTO.getNome());
    }

}
