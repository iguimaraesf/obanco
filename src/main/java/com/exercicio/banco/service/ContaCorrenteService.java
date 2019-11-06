package com.exercicio.banco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercicio.banco.controller.CadastroBean;
import com.exercicio.banco.domain.Conta;
import com.exercicio.banco.exception.CpfExistenteException;
import com.exercicio.banco.exception.EmailExistenteException;
import com.exercicio.banco.repository.ContaRepository;
import com.exercicio.banco.repository.CorrentistaRepository;

@Service
public class ContaCorrenteService {
	@Autowired
	private CorrentistaRepository repository;
	
	@Autowired
	private ContaRepository contaRepository;

	public Conta criarConta(CadastroBean cad) throws CpfExistenteException, EmailExistenteException {
		return null;
	}
}
