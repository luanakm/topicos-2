package com.topicos.dois.util;

import com.topicos.dois.dto.request.AgendaRequestDTO;
import com.topicos.dois.dto.response.AgendaResponseDTO;
import com.topicos.dois.dto.response.FuncionarioResponseDTO;
import com.topicos.dois.entity.Agenda;
import com.topicos.dois.entity.Funcionario;
import com.topicos.dois.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AgendaMapper {

    private final FuncionarioRepository funcionarioRepository;

    public Agenda toAgenda(AgendaRequestDTO agendaRequestDTO) {
        Funcionario funcionario = funcionarioRepository.findById(agendaRequestDTO.getFuncionarioId())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        return Agenda.builder()
                .dataHora(agendaRequestDTO.getDataHora())
                .nome(agendaRequestDTO.getNome())
                .servico(agendaRequestDTO.getServico())
                .funcionario(funcionario)
                .build();
    }

    public AgendaResponseDTO toAgendaDTO(Agenda agenda) {
        return new AgendaResponseDTO(agenda);
    }

    public List<AgendaResponseDTO> toAgendaDTO(List<Agenda> listaDeAgenda) {
        return listaDeAgenda.stream().map(AgendaResponseDTO::new).collect(Collectors.toList());
    }

    public void atualizarAgenda(Agenda agenda, Funcionario funcionario, AgendaRequestDTO agendaDTO) {

        agenda.setDataHora(agendaDTO.getDataHora());
        agenda.setNome(agendaDTO.getNome());
        agenda.setServico(agendaDTO.getServico());
        agenda.setFuncionario(funcionario);

    }

}
