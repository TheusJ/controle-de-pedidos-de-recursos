package com.matheus.controlepedidosrecursos.service;

import com.matheus.controlepedidosrecursos.dto.RelatorioDTO;

import java.util.List;

public interface RelatorioService {

    List<RelatorioDTO> buscarPorRelatorio(Long idFuncionario, RelatorioDTO relatorioDTO);
}
