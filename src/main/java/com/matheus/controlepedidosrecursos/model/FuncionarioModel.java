package com.matheus.controlepedidosrecursos.model;

import com.matheus.controlepedidosrecursos.enums.TipoCargoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_funcionario")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;


    @NotBlank
    private String cpf;


    @NotBlank
    @Column(unique = true)
    private String email;

//    @NotBlank
//    private String password;


    @Enumerated(EnumType.STRING)
    private TipoCargoEnum cargo;


}
