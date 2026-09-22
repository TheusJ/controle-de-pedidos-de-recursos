package com.matheus.controlepedidosrecursos.controller;

import com.matheus.controlepedidosrecursos.dto.FuncionarioDTO;
import com.matheus.controlepedidosrecursos.service.serviceImpl.FuncionarioServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/funcionarios")
public class FuncionarioController {

    @Autowired
    FuncionarioServiceImpl funcionarioServiceImpl;

    @PostMapping
    @Operation(summary = "Cadastrar Funcionário", description = "Apenas funcionários com cargo RH podem cadastrar Funcionários no Sistema. Este EndPoint tem Valdação de Dados(CPF, EMAIL, etc..), podendo gerar EXCEPTIONS, caso tenha algum dessas informações inválidas. Não é permitido cadastrar o mesmo funcionário com o mesmo CPF ou EMAIL.")
    public ResponseEntity<FuncionarioDTO> cadastrarFuncionario(@Valid @RequestBody FuncionarioDTO funcionarioDTO){
        FuncionarioDTO funcionarioCadastrado = funcionarioServiceImpl.cadastrarFuncionario(funcionarioDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioCadastrado);
    }




}
