package com.exercicio.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercicio.banco.domain.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long> {
	
}
