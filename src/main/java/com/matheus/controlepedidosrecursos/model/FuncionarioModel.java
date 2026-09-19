package com.matheus.controlepedidosrecursos.model;

import com.matheus.controlepedidosrecursos.enums.TipoCargoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "tb_funcionario")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @CPF
    private String cpf;

    @Email
    private String email;


    @Enumerated(EnumType.STRING)
    private TipoCargoEnum cargo;
}
