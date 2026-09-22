package com.matheus.controlepedidosrecursos.service;

import com.matheus.controlepedidosrecursos.dto.SolicitacaoDTO;

import java.util.List;

public interface SolicitacaoService {
    SolicitacaoDTO solicitarRecurso(Long idFuncionario, SolicitacaoDTO solicitacaoDTO);
    SolicitacaoDTO buscarSolicitacao(Long idSolicitacao, Long idFuncionario);
    List<SolicitacaoDTO> buscarTodasSolicitacoes(Long idFuncionario);
    SolicitacaoDTO alterarSolicitacao(Long idSolicitacao, Long idFuncionario, SolicitacaoDTO solicitacaoDTO);
}
