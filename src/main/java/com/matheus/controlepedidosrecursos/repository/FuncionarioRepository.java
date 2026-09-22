package com.matheus.controlepedidosrecursos.repository;

import com.matheus.controlepedidosrecursos.model.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, Long> {
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);

    Long id(Long id);
}
