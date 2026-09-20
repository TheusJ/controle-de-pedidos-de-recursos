package com.matheus.controlepedidosrecursos.dto;

import com.matheus.controlepedidosrecursos.enums.TipoCargoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDTO {

    private Long id;

    private String nome;

    @Column(unique = true)
    @CPF
    private String cpf;

    @Column(unique = true)
    @Email
    private String email;


    @Enumerated(EnumType.STRING)
    private TipoCargoEnum cargo;


}
