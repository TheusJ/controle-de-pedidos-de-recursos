package com.matheus.controlepedidosrecursos.service.serviceImpl;

import com.matheus.controlepedidosrecursos.dto.SolicitacaoDTO;
import com.matheus.controlepedidosrecursos.enums.SetoresEnum;
import com.matheus.controlepedidosrecursos.enums.StatusSolicitacaoEnum;
import com.matheus.controlepedidosrecursos.enums.TipoCargoEnum;
import com.matheus.controlepedidosrecursos.exception.SolicitacaoBuscaNaoAutorizada;
import com.matheus.controlepedidosrecursos.model.FuncionarioModel;
import com.matheus.controlepedidosrecursos.model.ProdutoSolicitadoModel;
import com.matheus.controlepedidosrecursos.model.SolicitacaoModel;
import com.matheus.controlepedidosrecursos.repository.FuncionarioRepository;
import com.matheus.controlepedidosrecursos.repository.ProdutoSolicitadoRepository;
import com.matheus.controlepedidosrecursos.repository.SolicitacaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class SolicitacaoServiceImplTest {


    @MockitoBean
    SolicitacaoRepository solicitacaoRepository;

    @Autowired
    SolicitacaoServiceImpl solicitacaoService;

    @MockitoBean
    ProdutoSolicitadoRepository produtoSolicitadoRepository;

    @MockitoBean
    FuncionarioRepository funcionarioRepository;

    @Test
    void alterarSolicitacaoDeveRetornarUmaSolicitacaoAlteradaSeTodosOsParametrosForemCorretos() {
        FuncionarioModel funcionarioModel1 = new FuncionarioModel(1L, "Matheus José", "10953762416", "mathjjc72@gmail.com", TipoCargoEnum.COMUM);

        FuncionarioModel funcionarioModel2 = new FuncionarioModel(2L, "Matheus José", "68419417483", "marcos@gmail.com", TipoCargoEnum.RH);

        FuncionarioModel funcionarioModel3 = new FuncionarioModel(3L, "Matheus José", "68419417483", "marcos@gmail.com", TipoCargoEnum.COMPRADOR);

        BigDecimal valorProduto = new BigDecimal(4500);
        BigDecimal valorTotalProduto = valorProduto.multiply(BigDecimal.valueOf(5L));



        List<ProdutoSolicitadoModel> produtosFuncionario = List.of(new ProdutoSolicitadoModel(1L, "Notebook", valorProduto, 5L, valorTotalProduto), new ProdutoSolicitadoModel(2L, "Computador", valorProduto, 1L, valorTotalProduto));

        List<ProdutoSolicitadoModel> produtosFuncionario2 = List.of(new ProdutoSolicitadoModel(3L, "Notebook", valorProduto, 5L, valorTotalProduto), new ProdutoSolicitadoModel(4L, "Computador", valorProduto, 1L, valorTotalProduto));

        List<ProdutoSolicitadoModel> produtosFuncionario3 = List.of(new ProdutoSolicitadoModel(5L, "Notebook", valorProduto, 5L, valorTotalProduto), new ProdutoSolicitadoModel(6L, "Computador", valorProduto, 1L, valorTotalProduto));

        BigDecimal valorTotalSolicitacao = valorTotalProduto.multiply(valorProduto);


        SolicitacaoModel solicitacao1 = new SolicitacaoModel(1L,funcionarioModel1, LocalDateTime.now(), SetoresEnum.DOCUMENTACAO, StatusSolicitacaoEnum.PENDENTE, null,  produtosFuncionario, null, valorTotalSolicitacao);

        SolicitacaoModel solicitacao2 = new SolicitacaoModel(2L,funcionarioModel2, LocalDateTime.now(), SetoresEnum.DOCUMENTACAO, StatusSolicitacaoEnum.PENDENTE, null,  produtosFuncionario2, null, valorTotalSolicitacao);

        SolicitacaoModel solicitacao3 = new SolicitacaoModel(3L,funcionarioModel3, LocalDateTime.now(), SetoresEnum.DOCUMENTACAO, StatusSolicitacaoEnum.PENDENTE, null,  produtosFuncionario2, null, valorTotalSolicitacao);

        Mockito.when(this.solicitacaoRepository.findById(2L)).thenReturn(Optional.of(solicitacao2));
        Mockito.when(this.funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionarioModel1));



        SolicitacaoDTO solicitacaoDTO = SolicitacaoDTO.builder()
                .id(solicitacao1.getId())
                .setorSolicitacao(solicitacao1.getSetorSolicitacao())
                .valorTotalSolicitacao(solicitacao1.getValorTotalSolicitacao())
                .funcionarioSolicitacao(solicitacao1.getFuncionarioSolicitacao())
                .build();

        SolicitacaoDTO solicitacaoDTOTeste = SolicitacaoDTO.builder()
                .id(solicitacao2.getId())
                .setorSolicitacao(solicitacao2.getSetorSolicitacao())
                .valorTotalSolicitacao(solicitacao2.getValorTotalSolicitacao())
                .funcionarioSolicitacao(solicitacao2.getFuncionarioSolicitacao())
                .build();


//        SolicitacaoDTO solicitation2 = this.solicitacaoService.alterarSolicitacao(1L, 1L, solicitacaoDTOTeste);
        SolicitacaoDTO solicitacao = this.solicitacaoService.alterarSolicitacao(2L, 1L, solicitacaoDTOTeste);



        Assertions.assertEquals(2L, 1L, solicitacao.getId());

    }
}