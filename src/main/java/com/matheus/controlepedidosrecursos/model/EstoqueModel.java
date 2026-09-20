package com.matheus.controlepedidosrecursos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_estoque")
public class EstoqueModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



}
