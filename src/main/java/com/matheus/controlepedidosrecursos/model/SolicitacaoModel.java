package com.matheus.controlepedidosrecursos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matheus.controlepedidosrecursos.enums.SetoresEnum;
import com.matheus.controlepedidosrecursos.enums.StatusSolicitacaoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_solicitacao")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolicitacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "solicitante_id")
    private FuncionarioModel funcionarioSolicitacao;


    private LocalDateTime dataSolicitacao;


    private SetoresEnum setorSolicitacao;


    private StatusSolicitacaoEnum statusSolicitacao;

    private LocalDateTime dataCancelamento;

    @OneToMany
    private List<ProdutoSolicitadoModel> produtosSolicitados;

    @ManyToOne
    @JoinColumn(name = "aprovador_id")
    private FuncionarioModel funcionarioAprovador;

    private BigDecimal valorTotalSolicitacao;
}
