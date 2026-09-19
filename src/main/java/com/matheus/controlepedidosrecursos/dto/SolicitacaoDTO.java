package com.matheus.controlepedidosrecursos.dto;


import com.matheus.controlepedidosrecursos.enums.SetoresEnum;
import com.matheus.controlepedidosrecursos.enums.StatusSolicitacaoEnum;
import com.matheus.controlepedidosrecursos.model.FuncionarioModel;
import com.matheus.controlepedidosrecursos.model.ProdutoModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolicitacaoDTO {



    private Long id;


    private FuncionarioModel funcionarioSolicitacao;

    @CreationTimestamp
    private LocalDateTime dataSolicitacao;

    @Enumerated(EnumType.STRING)
    private SetoresEnum setorRecebimento;

    @Enumerated(EnumType.STRING)
    private StatusSolicitacaoEnum statusSolicitacao;

    private LocalDateTime dataCancelamento;


    private List<ProdutoModel> produtosSolicitados;
}
