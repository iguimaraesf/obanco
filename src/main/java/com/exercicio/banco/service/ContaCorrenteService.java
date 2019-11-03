package com.exercicio.banco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercicio.banco.controller.CadastroBean;
import com.exercicio.banco.domain.Conta;
import com.exercicio.banco.domain.Correntista;
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
		// 1 - Não deixa mais que 1 CPF na base de clientes.
		Correntista u = repository.findByCpf(cad.getCpf());
		if (u != null) throw new CpfExistenteException();

		// 2 - Apenas 1 email na base de clientes.
		u = repository.findByEmail(cad.getEmail());
		if (u != null) throw new EmailExistenteException();

		u = new Correntista();
		u.setNome(cad.getNome());
		u.setEmail(cad.getEmail());
		// TODO CRIPTOGRAFAR
		u.setSenha(cad.getSenha());

		repository.save(u);
		Conta c = new Conta();
		c.setCorrentista(u);
		
		return contaRepository.save(c);
	}
}
