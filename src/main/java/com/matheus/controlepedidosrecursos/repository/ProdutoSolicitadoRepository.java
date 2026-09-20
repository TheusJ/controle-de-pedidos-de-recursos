package com.matheus.controlepedidosrecursos.repository;

import com.matheus.controlepedidosrecursos.model.ProdutoSolicitadoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoSolicitadoRepository extends JpaRepository<ProdutoSolicitadoModel, Long> {

    ProdutoSolicitadoModel findByNome(String nome);
}
