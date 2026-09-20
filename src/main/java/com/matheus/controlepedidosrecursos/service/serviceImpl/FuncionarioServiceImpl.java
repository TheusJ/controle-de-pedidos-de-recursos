package com.matheus.controlepedidosrecursos.service.serviceImpl;

import com.matheus.controlepedidosrecursos.dto.FuncionarioDTO;
import com.matheus.controlepedidosrecursos.exception.FuncionarioCpfJaExistenteException;
import com.matheus.controlepedidosrecursos.exception.FuncionarioIdJaExistenteException;
import com.matheus.controlepedidosrecursos.model.FuncionarioModel;
import com.matheus.controlepedidosrecursos.repository.FuncionarioRepository;
import com.matheus.controlepedidosrecursos.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    FuncionarioRepository funcionarioRepository;


    @Override
    public FuncionarioDTO cadastrarFuncionario(FuncionarioDTO funcionarioDTO) {

        FuncionarioModel funcionarioModel = FuncionarioModel.builder()
                .nome(funcionarioDTO.getNome())
                .cpf(funcionarioDTO.getCpf())
                .email(funcionarioDTO.getEmail())
                .cargo(funcionarioDTO.getCargo())
                .build();

        boolean existCpf = funcionarioRepository.existsByCpf(funcionarioModel.getCpf());
        boolean existEmail = funcionarioRepository.existsByEmail(funcionarioModel.getEmail());

        if(existCpf){
            throw new FuncionarioCpfJaExistenteException("CPF - Já existe um cadastro com este CPF ");
        }

        if(existEmail){
            throw new FuncionarioCpfJaExistenteException("Email - Já existe um cadastro com este Email ");
        }

        FuncionarioModel funcionarioSalvo = funcionarioRepository.save(funcionarioModel);

        FuncionarioDTO funcionarioDtoClient = FuncionarioDTO.builder()
                .id(funcionarioModel.getId())
                .nome(funcionarioModel.getNome())
                .cpf(funcionarioModel.getCpf())
                .email(funcionarioModel.getEmail())
                .cargo(funcionarioModel.getCargo())
                .build();

        return funcionarioDtoClient;

    }
}
