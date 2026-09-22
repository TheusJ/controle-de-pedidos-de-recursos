package com.matheus.controlepedidosrecursos.repository;

import com.matheus.controlepedidosrecursos.enums.SetoresEnum;
import com.matheus.controlepedidosrecursos.enums.StatusSolicitacaoEnum;
import com.matheus.controlepedidosrecursos.enums.TipoCargoEnum;
import com.matheus.controlepedidosrecursos.model.FuncionarioModel;
import com.matheus.controlepedidosrecursos.model.ProdutoSolicitadoModel;
import com.matheus.controlepedidosrecursos.model.SolicitacaoModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@DataJpaTest
@ActiveProfiles("test")
class SolicitacaoRepositoryTest {


    @Autowired
    SolicitacaoRepository solicitacaoRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    ProdutoSolicitadoRepository produtoSolicitadoRepository;

    @Test
    public void deveSerCapazDeEncontrarUmaSolicitacaoPorSetorE_TambemPorStatus(){
        // arrange
        BigDecimal valorTotal = new BigDecimal(450);
        BigDecimal valorProduto = new BigDecimal(4500);
        BigDecimal valorTotalProduto = valorProduto.multiply(valorTotal);
        List<ProdutoSolicitadoModel> produtoSolicitadoModels = Arrays.asList(new ProdutoSolicitadoModel(null,"Notebook", valorProduto, 5L, valorTotalProduto));

        produtoSolicitadoRepository.saveAll(produtoSolicitadoModels);

        FuncionarioModel funcionarioModel = new FuncionarioModel(null, "Matheus Jose", "10953762416", "mathjjc72@gmail.com", TipoCargoEnum.RH);

        funcionarioRepository.save(funcionarioModel);

        SolicitacaoModel nova = new SolicitacaoModel(null, funcionarioModel, LocalDateTime.now(), SetoresEnum.FINANCEIRO, StatusSolicitacaoEnum.PENDENTE, null, produtoSolicitadoModels, null, valorTotal);

        solicitacaoRepository.save(nova);


        boolean a = solicitacaoRepository.existsBySetorSolicitacaoAndStatusSolicitacao(SetoresEnum.FINANCEIRO, StatusSolicitacaoEnum.PENDENTE);


        Assertions.assertTrue(a);
    }

}