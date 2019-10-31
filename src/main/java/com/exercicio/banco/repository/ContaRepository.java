package com.exercicio.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercicio.banco.domain.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

}
