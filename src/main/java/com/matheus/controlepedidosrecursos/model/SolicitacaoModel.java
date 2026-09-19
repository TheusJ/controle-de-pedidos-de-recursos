package com.matheus.controlepedidosrecursos.model;

import com.matheus.controlepedidosrecursos.enums.SetoresEnum;
import com.matheus.controlepedidosrecursos.enums.StatusSolicitacaoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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
    @JoinColumn(name = "solicitacao_id")
    private FuncionarioModel funcionarioSolicitacao;


    private LocalDateTime dataSolicitacao;


    private SetoresEnum setorRecebimento;


    private StatusSolicitacaoEnum statusSolicitacao;

    private LocalDateTime dataCancelamento;

    @OneToMany
    private List<ProdutoModel> produtosSolicitados;
}
