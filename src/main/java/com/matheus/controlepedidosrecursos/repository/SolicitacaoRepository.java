package com.matheus.controlepedidosrecursos.repository;


import com.matheus.controlepedidosrecursos.enums.SetoresEnum;
import com.matheus.controlepedidosrecursos.enums.StatusSolicitacaoEnum;
import com.matheus.controlepedidosrecursos.model.SolicitacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitacaoRepository extends JpaRepository<SolicitacaoModel, Long> {

    boolean existsBySetorSolicitacaoAndStatusSolicitacao(SetoresEnum setorSolicitacao, StatusSolicitacaoEnum statusSolicitacao);

}
