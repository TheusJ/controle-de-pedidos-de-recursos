package com.matheus.controlepedidosrecursos.service.serviceImpl;

import com.matheus.controlepedidosrecursos.dto.SolicitacaoDTO;
import com.matheus.controlepedidosrecursos.enums.StatusSolicitacaoEnum;
import com.matheus.controlepedidosrecursos.exception.FuncionarioIdInvalidoException;
import com.matheus.controlepedidosrecursos.exception.SetorComSolicitacaoPendenteException;
import com.matheus.controlepedidosrecursos.model.FuncionarioModel;
import com.matheus.controlepedidosrecursos.model.ProdutoSolicitadoModel;
import com.matheus.controlepedidosrecursos.model.SolicitacaoModel;
import com.matheus.controlepedidosrecursos.repository.FuncionarioRepository;
import com.matheus.controlepedidosrecursos.repository.ProdutoRepository;
import com.matheus.controlepedidosrecursos.repository.ProdutoSolicitadoRepository;
import com.matheus.controlepedidosrecursos.repository.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SolicitacaoServiceImpl {

    @Autowired
    SolicitacaoRepository solicitacaoRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    ProdutoSolicitadoRepository produtoSolicitadoRepositoryRepository;

    public SolicitacaoDTO solicitarRecurso(Long idFuncionario, SolicitacaoDTO solicitacaoDTO) {
        FuncionarioModel existFuncionario = funcionarioRepository.findById(idFuncionario).orElseThrow(() -> new FuncionarioIdInvalidoException("ID - Funcionario não encontrado"));


        boolean verificacaoSetorBySolicitacao = solicitacaoRepository.existsBySetorRecebimentoAndStatusSolicitacao(solicitacaoDTO.getSetorRecebimento(), StatusSolicitacaoEnum.PENDENTE);
        if(verificacaoSetorBySolicitacao){
            throw new SetorComSolicitacaoPendenteException("Setor já tem uma solicitação pendente!");
        }

        SolicitacaoModel solicitacaoModel = SolicitacaoModel.builder()
                .funcionarioSolicitacao(existFuncionario)
                .dataSolicitacao(solicitacaoDTO.getDataSolicitacao())
                .setorRecebimento(solicitacaoDTO.getSetorRecebimento())
                .statusSolicitacao(StatusSolicitacaoEnum.PENDENTE)
                .build();


        BigDecimal valorTotal = solicitacaoModel.getProdutosSolicitados().stream().map(ProdutoSolicitadoModel::getValorProduto).reduce(BigDecimal.ZERO, BigDecimal::add);

        List<ProdutoSolicitadoModel> produtoSolicitadoSalvo = produtoSolicitadoRepositoryRepository.saveAll(solicitacaoModel.getProdutosSolicitados());
        SolicitacaoModel solicitacalSalva = solicitacaoRepository.save(solicitacaoModel);

        SolicitacaoDTO solicitacaoDtoCliente = SolicitacaoDTO.builder()
                .id(solicitacalSalva.getId())
                .funcionarioSolicitacao(existFuncionario)
                .dataSolicitacao(LocalDateTime.now())
                .setorRecebimento(solicitacalSalva.getSetorRecebimento())
                .statusSolicitacao(StatusSolicitacaoEnum.PENDENTE)
                .valorTotalSolicitacao(valorTotal)
                .build();


        return solicitacaoDtoCliente;


    }
}
