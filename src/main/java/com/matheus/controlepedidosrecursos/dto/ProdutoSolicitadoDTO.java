package com.matheus.controlepedidosrecursos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoSolicitadoDTO {


    private Long id;

    private String nome;

    @Positive(message = "Somente valores positivos")
    private BigDecimal valorProduto;

    @Positive(message = "Somente valores positivos")
    private Long quantidades;

    @Positive(message = "Somente valores positivos")
    private BigDecimal valorTotalSolicitacao;
}
