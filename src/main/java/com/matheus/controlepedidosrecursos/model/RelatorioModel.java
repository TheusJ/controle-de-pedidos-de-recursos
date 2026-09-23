package com.matheus.controlepedidosrecursos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_relatorio")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime dataRelatorio;

    @JoinColumn(name = "relatorios_produtos_solicitados_id")
    @OneToMany
    List<ProdutoSolicitadoModel> relatoriosProdutosSolicitados;
}
