package com.matheus.controlepedidosrecursos.controller;

import com.matheus.controlepedidosrecursos.dto.FuncionarioDTO;
import com.matheus.controlepedidosrecursos.service.serviceImpl.FuncionarioServiceImpl;
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
    public ResponseEntity<FuncionarioDTO> cadastrarFuncionario(@RequestBody FuncionarioDTO funcionarioDTO){
        FuncionarioDTO funcionarioCadastrado = funcionarioServiceImpl.cadastrarFuncionario(funcionarioDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioCadastrado);
    }




}
