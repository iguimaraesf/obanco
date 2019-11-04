package com.exercicio.banco.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.exercicio.banco.domain.Correntista;

public interface CorrentistaRepository extends PagingAndSortingRepository<Correntista, Long> {
	public Correntista findByCpf(Long cpf);

	public Correntista findByEmail(String email);
}
