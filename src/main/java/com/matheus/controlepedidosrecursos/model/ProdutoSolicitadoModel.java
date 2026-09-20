package com.matheus.controlepedidosrecursos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_item_solicitado")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoSolicitadoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Positive(message = "Somente valores positivos")
    private BigDecimal valorProduto;

    @Positive(message = "Somente valores positivos")
    private Integer quantidades;

    @Positive(message = "Somente valores positivos")
    private BigDecimal valorTotalSolicitacao;

}
