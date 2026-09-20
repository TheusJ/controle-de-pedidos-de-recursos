package com.matheus.controlepedidosrecursos.service;

import com.matheus.controlepedidosrecursos.dto.SolicitacaoDTO;

public interface SolicitacaoService {
    SolicitacaoDTO solicitarRecurso(Long idFuncionario, SolicitacaoDTO solicitacaoDTO);
    SolicitacaoDTO buscarSolicitacao(Long idFuncionario);
}
