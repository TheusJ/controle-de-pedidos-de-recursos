package com.matheus.controlepedidosrecursos.service.serviceImpl;

import com.matheus.controlepedidosrecursos.dto.FuncionarioDTO;
import com.matheus.controlepedidosrecursos.dto.RelatorioDTO;
import com.matheus.controlepedidosrecursos.enums.TipoCargoEnum;
import com.matheus.controlepedidosrecursos.exception.FuncionarioIdInvalidoException;
import com.matheus.controlepedidosrecursos.exception.FuncionarioSemAutorizacaoException;
import com.matheus.controlepedidosrecursos.model.FuncionarioModel;
import com.matheus.controlepedidosrecursos.repository.FuncionarioRepository;
import com.matheus.controlepedidosrecursos.repository.ProdutoSolicitadoRepository;
import com.matheus.controlepedidosrecursos.repository.RelatorioRepository;
import com.matheus.controlepedidosrecursos.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioServiceImpl implements RelatorioService {
    @Autowired
    ProdutoSolicitadoRepository produtoSolicitadoRepository;

    @Autowired
    RelatorioRepository relatorioRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    public List<RelatorioDTO> buscarPorRelatorio(Long idFuncionario, RelatorioDTO relatorioDTO){
        FuncionarioModel funcionarioEncontrado = funcionarioRepository.findById(idFuncionario).orElseThrow(() -> new FuncionarioIdInvalidoException("ID - Funcionário não encontrado!"));

        if(funcionarioEncontrado.getCargo() != TipoCargoEnum.RH){
            throw new FuncionarioSemAutorizacaoException("Você não tem autorização!");
        }
    }
}
