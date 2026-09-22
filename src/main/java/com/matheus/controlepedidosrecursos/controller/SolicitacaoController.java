package com.matheus.controlepedidosrecursos.controller;

import com.matheus.controlepedidosrecursos.dto.SolicitacaoDTO;
import com.matheus.controlepedidosrecursos.service.serviceImpl.SolicitacaoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/solicitacao")
@Tag(name = "Solicitações", description = "Gerenciamento de Solicitações de Recursos")
public class SolicitacaoController {

    @Autowired
    SolicitacaoServiceImpl solicitacaoService;


    @PostMapping("/{funcionarioId}")
    @Operation(summary = "Solicitar Recursos", description = "Solicitar Recursos, podendo solicitar apenas uma vez, caso tiver com o setor com Status Pendente não pode solicitar outros Recursos. Tendo, também, validação de usuário(Verificação se usuário existe) e verificação de Status por Setor.")
    public ResponseEntity<SolicitacaoDTO> solicitarRecurso(@PathVariable Long funcionarioId, @RequestBody SolicitacaoDTO solicitacaoDTO){
        SolicitacaoDTO solicitacaoModel = solicitacaoService.solicitarRecurso(funcionarioId, solicitacaoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(solicitacaoModel);
    }

    @GetMapping("/{idSolicitacao}/{idFuncionario}")
    @Operation(summary = "Buscar Solicitação", description = "Buscar Solicitação, aqui se busca solicitação por ID. Contendo verificação se a Solicitação existe, podendo gerar EXCEPTION. Busca por usuário, o usuário que está sendo passado [ ID ], não pode ver Solicitações que não foram solicitados por ele. Podendo gerar, também, EXCEPTIONS. Usuários que tem solicitação, poderão apenas ver suas próprias Solicitações, Usuários com cargos: COMPRADOR e RH, podem ver qualquer solicitações, bastando apenas colocar o [ ID - Solicitação ] juntamente com o seu [ ID - Funcionario ].")
    public ResponseEntity<SolicitacaoDTO> buscarSolicitacao(@PathVariable Long idSolicitacao, @PathVariable Long idFuncionario){

    	SolicitacaoDTO buscarSolicitacao = solicitacaoService.buscarSolicitacao(idSolicitacao, idFuncionario);

    	return ResponseEntity.ok(buscarSolicitacao);

    }

    @GetMapping("/{idFuncionario}")
    @Operation(summary = "Buscar todas Solicitações", description = "Buscar todas Solicitações. Apenas Usuário com cargos COMPRADOR e RH podem ver todas as solicitações do Sistema. Usuários com cargos diferentes podem ver apenas suas Solicitações durante todo o Processo.")
    public ResponseEntity<List<SolicitacaoDTO>> buscarTodasSolicitacoes(@PathVariable Long idFuncionario){

        List<SolicitacaoDTO> todasSolicitacoes = solicitacaoService.buscarTodasSolicitacoes(idFuncionario);

        return ResponseEntity.ok(todasSolicitacoes);
    }

    @PutMapping("/{idSolicitacao}/{idFuncionario}")
    public ResponseEntity<SolicitacaoDTO> alterarSolicitacao(@PathVariable Long idSolicitacao, @PathVariable Long idFuncionario, @RequestBody SolicitacaoDTO solicitacaoDTOAlterar){

        SolicitacaoDTO solicitacaoEncontrado = solicitacaoService(idSolicitacao, idFuncionario);


        return ResponseEntity.status(HttpStatus.OK).body(solicitacaoEncontrado);
    }
}
