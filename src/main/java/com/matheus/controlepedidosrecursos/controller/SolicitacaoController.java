package com.matheus.controlepedidosrecursos.controller;

import com.matheus.controlepedidosrecursos.dto.SolicitacaoDTO;
import com.matheus.controlepedidosrecursos.service.serviceImpl.SolicitacaoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/solicitacao")
public class SolicitacaoController {

    @Autowired
    SolicitacaoServiceImpl solicitacaoService;

    @PostMapping("{funcionarioId}")
    public ResponseEntity<SolicitacaoDTO> solicitarRecurso(@PathVariable Long funcionarioId, @RequestBody SolicitacaoDTO solicitacaoDTO){
        SolicitacaoDTO solicitacaoModel = solicitacaoService.solicitarRecurso(funcionarioId, solicitacaoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(solicitacaoModel);
    }

    @GetMapping
    public ResponseEntity<SolicitacaoDTO> buscarSolicitacao(@PathVariable Long funcionarioId){
        return null;
    }
}
