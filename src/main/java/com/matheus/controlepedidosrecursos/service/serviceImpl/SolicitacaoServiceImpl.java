package com.matheus.controlepedidosrecursos.service.serviceImpl;

import com.matheus.controlepedidosrecursos.dto.FuncionarioDTO;
import com.matheus.controlepedidosrecursos.dto.ProdutoSolicitadoDTO;
import com.matheus.controlepedidosrecursos.dto.SolicitacaoDTO;
import com.matheus.controlepedidosrecursos.enums.StatusSolicitacaoEnum;
import com.matheus.controlepedidosrecursos.enums.TipoCargoEnum;
import com.matheus.controlepedidosrecursos.exception.*;
import com.matheus.controlepedidosrecursos.model.FuncionarioModel;
import com.matheus.controlepedidosrecursos.model.ProdutoSolicitadoModel;
import com.matheus.controlepedidosrecursos.model.SolicitacaoModel;
import com.matheus.controlepedidosrecursos.repository.FuncionarioRepository;
import com.matheus.controlepedidosrecursos.repository.ProdutoSolicitadoRepository;
import com.matheus.controlepedidosrecursos.repository.SolicitacaoRepository;
import com.matheus.controlepedidosrecursos.service.SolicitacaoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolicitacaoServiceImpl implements SolicitacaoService {

    @Autowired
    SolicitacaoRepository solicitacaoRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    ProdutoSolicitadoRepository produtoSolicitadoRepositoryRepository;

    public SolicitacaoDTO solicitarRecurso(Long idFuncionario, SolicitacaoDTO solicitacaoDTO) {
        FuncionarioModel existFuncionario = funcionarioRepository.findById(idFuncionario).orElseThrow(() -> new FuncionarioIdInvalidoException("ID - Funcionario não encontrado"));


        boolean verificacaoSetorBySolicitacao = solicitacaoRepository.existsBySetorSolicitacaoAndStatusSolicitacao(solicitacaoDTO.getSetorSolicitacao(), StatusSolicitacaoEnum.PENDENTE);

        if (verificacaoSetorBySolicitacao) {
            throw new SetorComSolicitacaoPendenteException("Setor já tem uma solicitação pendente!");
        }


        SolicitacaoModel solicitacaoModel = SolicitacaoModel.builder()
                .funcionarioSolicitacao(existFuncionario)
                .dataSolicitacao(LocalDateTime.now())
                .setorSolicitacao(solicitacaoDTO.getSetorSolicitacao())
                .statusSolicitacao(StatusSolicitacaoEnum.PENDENTE)
                .produtosSolicitados(solicitacaoDTO.getProdutosSolicitados())
                .build();


        BigDecimal valorSolicitacaoTotal = BigDecimal.ZERO;

        for (ProdutoSolicitadoModel produtos : solicitacaoModel.getProdutosSolicitados()) {

            BigDecimal valorProdutoTotal = produtos.getValorProduto().multiply(BigDecimal.valueOf(produtos.getQuantidades()));


            produtos.setValorTotalProdutoSolicitacao(valorProdutoTotal);


            valorSolicitacaoTotal = valorSolicitacaoTotal.add(valorProdutoTotal);


        }

        solicitacaoModel.setValorTotalSolicitacao(valorSolicitacaoTotal);


        List<ProdutoSolicitadoModel> produtoSolicitadoSalvo = produtoSolicitadoRepositoryRepository.saveAll(solicitacaoModel.getProdutosSolicitados());

        SolicitacaoModel solicitacalSalva = solicitacaoRepository.save(solicitacaoModel);

        SolicitacaoDTO solicitacaoDtoCliente = SolicitacaoDTO.builder()
                .id(solicitacalSalva.getId())
                .funcionarioSolicitacao(existFuncionario)
                .dataSolicitacao(solicitacalSalva.getDataSolicitacao())
                .setorSolicitacao(solicitacalSalva.getSetorSolicitacao())
                .statusSolicitacao(StatusSolicitacaoEnum.PENDENTE)
                .produtosSolicitados(solicitacalSalva.getProdutosSolicitados())
                .valorTotalSolicitacao(solicitacalSalva.getValorTotalSolicitacao())
                .build();


        return solicitacaoDtoCliente;


    }

    @Override
    public SolicitacaoDTO buscarSolicitacao(Long idSolicitacao, Long idFuncinario) {

        SolicitacaoModel findSolicitacaoById = solicitacaoRepository.findById(idSolicitacao).orElseThrow(() -> new SolicitacaoIdNaoEncontradoException("Solicitação não encontrada!"));

        FuncionarioModel findFuncinario = funcionarioRepository.findById(idFuncinario).orElseThrow(() -> new FuncionarioIdInvalidoException("Funcionario não encontrado!"));

        if (!findSolicitacaoById.getFuncionarioSolicitacao().equals(findFuncinario) && !findFuncinario.getCargo().equals(TipoCargoEnum.RH) && !findFuncinario.getCargo().equals(TipoCargoEnum.COMPRADOR)) {
            throw new SolicitacaoBuscaNaoAutorizada("Você não pode ver uma solicitação que não foi sua!");
        }

        SolicitacaoDTO solicitacaoDTO = SolicitacaoDTO.builder()
                .id(idSolicitacao)
                .dataSolicitacao(findSolicitacaoById.getDataSolicitacao())
                .statusSolicitacao(findSolicitacaoById.getStatusSolicitacao())
                .setorSolicitacao(findSolicitacaoById.getSetorSolicitacao())
                .funcionarioSolicitacao(findSolicitacaoById.getFuncionarioSolicitacao())
                .valorTotalSolicitacao(findSolicitacaoById.getValorTotalSolicitacao())
                .produtosSolicitados(findSolicitacaoById.getProdutosSolicitados())
                .build();

        return solicitacaoDTO;
    }

    @Override
    public List<SolicitacaoDTO> buscarTodasSolicitacoes(Long idFuncionario) {
        FuncionarioModel verificarFuncionario = funcionarioRepository.findById(idFuncionario).orElseThrow(() -> new FuncionarioIdInvalidoException("Funcionario não encontrado!"));

        if (!verificarFuncionario.getCargo().equals(TipoCargoEnum.COMPRADOR) || verificarFuncionario.getCargo().equals(TipoCargoEnum.RH)) {
            throw new FuncionarioSemAutorizacaoException("Funcionario não tem autorização para esta requisição!");
        }

        List<SolicitacaoModel> allSolicitacaoModel = solicitacaoRepository.findAll();

        List<SolicitacaoDTO> allSolicitacaoDTO = allSolicitacaoModel.stream().map(solicitacaoModel -> SolicitacaoDTO.builder()
                        .id(solicitacaoModel.getId())
                        .dataSolicitacao(solicitacaoModel.getDataSolicitacao())
                        .statusSolicitacao(solicitacaoModel.getStatusSolicitacao())
                        .setorSolicitacao(solicitacaoModel.getSetorSolicitacao())
                        .funcionarioSolicitacao(solicitacaoModel.getFuncionarioSolicitacao())
                        .valorTotalSolicitacao(solicitacaoModel.getValorTotalSolicitacao())
                        .build())
                .collect(Collectors.toList());

        return allSolicitacaoDTO;

    }


    @Override
    public SolicitacaoDTO alterarSolicitacao(Long idSolicitacao, Long idFuncionario, SolicitacaoDTO solicitacaoDTO) {
        SolicitacaoModel solicitacaoEncontrado = solicitacaoRepository.findById(idSolicitacao).orElseThrow(() -> new SolicitacaoIdNaoEncontradoException("ID - Solicitação não encontrado"));
        FuncionarioModel funcionarioEncontrado = funcionarioRepository.findById(idFuncionario).orElseThrow(() -> new FuncionarioIdInvalidoException("ID - Funcionário não encontrado!"));


        if (!funcionarioEncontrado.getCargo().equals(TipoCargoEnum.RH) && !funcionarioEncontrado.getCargo().equals(TipoCargoEnum.COMPRADOR) && !solicitacaoEncontrado.getFuncionarioSolicitacao().getCargo().equals(funcionarioEncontrado.getCargo())) {
            throw new SolicitacaoBuscaNaoAutorizada("Busca não autorizada!");
        }

        SolicitacaoModel solicitacaoAtualizada = SolicitacaoModel.builder()
                .id(idSolicitacao)
                .dataSolicitacao(LocalDateTime.now())
                .setorSolicitacao(solicitacaoDTO.getSetorSolicitacao())
                .statusSolicitacao(solicitacaoDTO.getStatusSolicitacao())
                .produtosSolicitados(solicitacaoDTO.getProdutosSolicitados())
                .dataCancelamento(solicitacaoDTO.getDataCancelamento())
                .funcionarioSolicitacao(funcionarioEncontrado)
                .valorTotalSolicitacao(solicitacaoDTO.getValorTotalSolicitacao())
                .build();

        SolicitacaoModel solicitacaoModelSalva = solicitacaoAtualizada;

        SolicitacaoDTO solicitacaoAtualizadaDTO = SolicitacaoDTO.builder()
                .id(solicitacaoAtualizada.getId())
                .dataSolicitacao(LocalDateTime.now())
                .setorSolicitacao(solicitacaoAtualizada.getSetorSolicitacao())
                .statusSolicitacao(solicitacaoAtualizada.getStatusSolicitacao())
                .produtosSolicitados(solicitacaoAtualizada.getProdutosSolicitados())
                .dataCancelamento(solicitacaoAtualizada.getDataCancelamento())
                .funcionarioSolicitacao(funcionarioEncontrado)
                .valorTotalSolicitacao(solicitacaoAtualizada.getValorTotalSolicitacao())
                .build();

        return solicitacaoAtualizadaDTO;
    }
}
