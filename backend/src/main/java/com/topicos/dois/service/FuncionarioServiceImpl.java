package com.topicos.dois.service;

import com.topicos.dois.dto.request.FuncionarioRequestDTO;
import com.topicos.dois.dto.response.FuncionarioResponseDTO;
import com.topicos.dois.entity.Funcionario;
import com.topicos.dois.repository.FuncionarioRepository;
import com.topicos.dois.util.FuncionarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Primary
@RequiredArgsConstructor
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    private final FuncionarioMapper funcionarioMapper;


    @Override
    public FuncionarioResponseDTO findById(Long id) {
     return funcionarioMapper.toFuncionarioDTO(returnFuncionario(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<FuncionarioResponseDTO> findByNome(String nome) {
        return funcionarioMapper.toFuncionariosDTO(returnFuncionarioByNome(nome));
    }

    @Override
    public List<FuncionarioResponseDTO> findAll() {
        return funcionarioMapper.toFuncionarioDTO(funcionarioRepository.findAll());
    }

    @Override
    public FuncionarioResponseDTO register(FuncionarioRequestDTO funcionarioRequestDTO) {

        Funcionario funcionario = funcionarioMapper.toFuncionario(funcionarioRequestDTO);

        return funcionarioMapper.toFuncionarioDTO(funcionarioRepository.save(funcionario));
    }

    @Override
    public FuncionarioResponseDTO update(Long id, FuncionarioRequestDTO funcionarioRequestDTO) {

        Funcionario funcionario = returnFuncionario(id);

        funcionarioMapper.atualizarFuncionario(funcionario, funcionarioRequestDTO);

        return funcionarioMapper.toFuncionarioDTO(funcionarioRepository.save(funcionario));
    }

    @Override
    public String delete(Long id) {
        funcionarioRepository.deleteById(id);
        return "Funcionario id: "+id+" deleted";
    }

    private Funcionario returnFuncionario(Long id) {
       return funcionarioRepository.findById(id)
               .orElseThrow(()-> new RuntimeException("Funcionario wasn't fount on database"));
    }

    private List<Funcionario> returnFuncionarioByNome(String nome) {
       return funcionarioRepository.findByNomeContainingIgnoreCase(nome);
    }

}
