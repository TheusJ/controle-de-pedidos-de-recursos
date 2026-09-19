package com.matheus.controlepedidosrecursos.service.serviceImpl;

import com.matheus.controlepedidosrecursos.dto.FuncionarioDTO;
import com.matheus.controlepedidosrecursos.dto.ProdutoDTO;
import com.matheus.controlepedidosrecursos.dto.SolicitacaoDTO;
import com.matheus.controlepedidosrecursos.enums.StatusSolicitacaoEnum;
import com.matheus.controlepedidosrecursos.exception.FuncionarioIdInvalido;
import com.matheus.controlepedidosrecursos.exception.SetorComSolicitacaoPendenteException;
import com.matheus.controlepedidosrecursos.exception.SoliciacaoIdInvalido;
import com.matheus.controlepedidosrecursos.model.FuncionarioModel;
import com.matheus.controlepedidosrecursos.model.ProdutoModel;
import com.matheus.controlepedidosrecursos.model.SolicitacaoModel;
import com.matheus.controlepedidosrecursos.repository.FuncionarioRepository;
import com.matheus.controlepedidosrecursos.repository.ProdutoRepository;
import com.matheus.controlepedidosrecursos.repository.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SolicitacaoServiceImpl {

    @Autowired
    SolicitacaoRepository solicitacaoRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    public SolicitacaoDTO solicitarRecurso(Long idFuncionario, SolicitacaoDTO solicitacaoDTO) {
        FuncionarioModel existFuncionario = funcionarioRepository.findById(idFuncionario).orElseThrow(() -> new FuncionarioIdInvalido("ID - Funcionario não encontrado"));


        boolean verificacaoSetorBySolicitacao = solicitacaoRepository.existsBySetorRecebimentoAndStatusSolicitacao(solicitacaoDTO.getSetorRecebimento(), StatusSolicitacaoEnum.PENDENTE);
        if(verificacaoSetorBySolicitacao){
            throw new SetorComSolicitacaoPendenteException("Setor já tem uma solicitação pendente!");
        }


        SolicitacaoModel solicitacaoRecurso = SolicitacaoModel.builder()
                .funcionarioSolicitacao(existFuncionario)
                .setorRecebimento(solicitacaoDTO.getSetorRecebimento())
                .statusSolicitacao(StatusSolicitacaoEnum.PENDENTE)
                .dataSolicitacao(LocalDateTime.now())
                .produtosSolicitados(solicitacaoDTO.getProdutosSolicitados())
                .build();


        List<ProdutoModel> produtosProcessamento = solicitacaoRecurso.getProdutosSolicitados();
        List<ProdutoModel> produtosSalvos = produtoRepository.saveAll(produtosProcessamento);
        SolicitacaoModel solicitacaoSalva = solicitacaoRepository.save(solicitacaoRecurso);

        SolicitacaoDTO solicitacaoDtoCliente = SolicitacaoDTO.builder()
                .id(solicitacaoRecurso.getId())
                .funcionarioSolicitacao(existFuncionario)
                .setorRecebimento(solicitacaoRecurso.getSetorRecebimento())
                .statusSolicitacao(StatusSolicitacaoEnum.PENDENTE)
                .dataSolicitacao(LocalDateTime.now())
                .produtosSolicitados(solicitacaoRecurso.getProdutosSolicitados())
                .build();

        return solicitacaoDtoCliente;

    }
}
