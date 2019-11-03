package com.exercicio.banco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercicio.banco.domain.Correntista;
import com.exercicio.banco.repository.CorrentistaRepository;

@Service
public class CorrentistaService {

	@Autowired
	private CorrentistaRepository repository;

	public List<Correntista> listarTodos() {
		return this.repository.findAll();
	}

	public Correntista findByEmail(String email) {
		return this.repository.findByEmail(email);
	}
}
