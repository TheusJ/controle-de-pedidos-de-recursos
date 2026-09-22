package com.matheus.controlepedidosrecursos.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matheus.controlepedidosrecursos.enums.SetoresEnum;
import com.matheus.controlepedidosrecursos.enums.StatusSolicitacaoEnum;
import com.matheus.controlepedidosrecursos.model.FuncionarioModel;
import com.matheus.controlepedidosrecursos.model.ProdutoSolicitadoModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolicitacaoDTO {



    private Long id;


    private FuncionarioModel funcionarioSolicitacao;

    @JsonFormat(pattern = "DD/mm/yyy HH:mm:ss")
    @CreationTimestamp
    private LocalDateTime dataSolicitacao;

    @Enumerated(EnumType.STRING)
    private SetoresEnum setorSolicitacao;

    @Enumerated(EnumType.STRING)
    private StatusSolicitacaoEnum statusSolicitacao;

    @JsonFormat(pattern = "DD/mm/yyy HH:mm:ss")
    private LocalDateTime dataCancelamento;


    private BigDecimal valorTotalSolicitacao;

    private FuncionarioModel funcionarioAprovador;

    private List<ProdutoSolicitadoModel> produtosSolicitados;
}
