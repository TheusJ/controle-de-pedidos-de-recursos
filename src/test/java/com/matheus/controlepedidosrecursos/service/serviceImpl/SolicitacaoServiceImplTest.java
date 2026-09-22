package com.matheus.controlepedidosrecursos.service.serviceImpl;

import com.matheus.controlepedidosrecursos.enums.SetoresEnum;
import com.matheus.controlepedidosrecursos.enums.StatusSolicitacaoEnum;
import com.matheus.controlepedidosrecursos.enums.TipoCargoEnum;
import com.matheus.controlepedidosrecursos.exception.FuncionarioIdInvalidoException;
import com.matheus.controlepedidosrecursos.exception.SolicitacaoIdNaoEncontradoException;
import com.matheus.controlepedidosrecursos.model.FuncionarioModel;
import com.matheus.controlepedidosrecursos.model.ProdutoSolicitadoModel;
import com.matheus.controlepedidosrecursos.model.SolicitacaoModel;
import com.matheus.controlepedidosrecursos.repository.FuncionarioRepository;
import com.matheus.controlepedidosrecursos.repository.ProdutoSolicitadoRepository;
import com.matheus.controlepedidosrecursos.repository.SolicitacaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Deve retornar uma solicitação se todas as validações baterem corretamente. Deve retornar exceptions se houver alguns dos parametros incorretos.")
class SolicitacaoServiceImplTest {

    @Mock
    FuncionarioRepository funcionarioRepository;

    @Mock
    SolicitacaoRepository solicitacaoRepository;

    @Mock
    ProdutoSolicitadoRepository produtoSolicitadoRepository;



    @InjectMocks
    SolicitacaoServiceImpl solicitacaoService;


    @Test
    @DisplayName("Deve retornar uma solicitação se todas as validações baterem corretamente. Deve retornar exceptions se houver alguns dos parametros incorretos.")
    public void deveRetornarUmaSolicitacaoExistente(){
        FuncionarioModel funcionarioTest = new FuncionarioModel(null, "Matheus José", "10953762416", "mathjjc72@gmail.com", TipoCargoEnum.RH);

        funcionarioRepository.save(funcionarioTest);
        BigDecimal valorProduto = new BigDecimal(4.500);
        BigDecimal valorTotalProduto = valorProduto.multiply(BigDecimal.valueOf(5L));
        BigDecimal valorTotalSolicitacaoAcumuladorTest = BigDecimal.ZERO;
        valorTotalSolicitacaoAcumuladorTest = valorTotalSolicitacaoAcumuladorTest.add(valorTotalProduto);


        List<ProdutoSolicitadoModel> produtosTest = Arrays.asList(new ProdutoSolicitadoModel(null,"Notebook", valorProduto, 5L, valorTotalProduto));



        SolicitacaoModel solicitacaoTeste = new SolicitacaoModel(null, funcionarioTest, LocalDateTime.now(), SetoresEnum.FINANCEIRO, StatusSolicitacaoEnum.PENDENTE, null, produtosTest, null, valorTotalSolicitacaoAcumuladorTest);





    }

    @Test
    public void deveRetornarUmaExceptionSeCasoNaoForOSolicitanteODonoDaSlicitacaoBuscadaOuQueNaoSejaDeCargoRhOuCompradorTest(){

        FuncionarioModel funcionarioTest = new FuncionarioModel(null, "Matheus José", "10953762416", "mathjjc72@gmail.com", TipoCargoEnum.COMUM);
        funcionarioRepository.save(funcionarioTest);
        BigDecimal valorProduto = new BigDecimal(4500);
        BigDecimal valorTotalProduto = valorProduto.multiply(BigDecimal.valueOf(5L));
        BigDecimal valorTotalSolicitacaoAcumuladorTest = BigDecimal.ZERO;
        valorTotalSolicitacaoAcumuladorTest = valorTotalSolicitacaoAcumuladorTest.add(valorTotalProduto);

        List<ProdutoSolicitadoModel> produtosTest = Arrays.asList(new ProdutoSolicitadoModel(null,"Notebook", valorProduto, 5L, valorTotalProduto));


        SolicitacaoModel solicitacaoEsperada = new SolicitacaoModel(null, funcionarioTest, LocalDateTime.now(), SetoresEnum.FINANCEIRO, StatusSolicitacaoEnum.PENDENTE, null, produtosTest, null, valorTotalSolicitacaoAcumuladorTest);







        when(solicitacaoRepository.findById(1L)).thenReturn(Optional.of(solicitacaoEsperada));
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionarioTest));

        Assertions.assertThrows(SolicitacaoIdNaoEncontradoException.class, () -> solicitacaoService.buscarSolicitacao(1L, 1L));
    }

}