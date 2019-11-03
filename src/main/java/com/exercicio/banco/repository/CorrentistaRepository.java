package com.exercicio.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercicio.banco.domain.Correntista;

public interface CorrentistaRepository extends JpaRepository<Correntista, Long> {
	public Correntista findByCpf(Long cpf);

	public Correntista findByEmail(String email);
}
