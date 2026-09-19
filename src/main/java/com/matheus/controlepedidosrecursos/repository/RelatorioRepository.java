package com.matheus.controlepedidosrecursos.repository;

import com.matheus.controlepedidosrecursos.model.RelatorioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelatorioRepository extends JpaRepository<RelatorioModel, Long> {
}
