package com.matheus.controlepedidosrecursos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "tb_produto")
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    @Pattern(regexp = "^[A-Z]{3}-[A-Z]{4}-\\d{2}$", message = "O sku do produto deve seguir o padrão AAA-AAAA-00")
    private String sku;
}
