package com.exercicio.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercicio.banco.domain.Historico;
import com.exercicio.banco.domain.HistoricoPK;

public interface HistoricoRepository extends JpaRepository<Historico, HistoricoPK> {

}
