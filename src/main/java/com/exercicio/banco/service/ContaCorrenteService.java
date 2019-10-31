package com.exercicio.banco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercicio.banco.controller.CadastroBean;
import com.exercicio.banco.domain.Conta;
import com.exercicio.banco.domain.Usuario;
import com.exercicio.banco.exception.CpfExistenteException;
import com.exercicio.banco.exception.EmailExistenteException;
import com.exercicio.banco.repository.ContaRepository;
import com.exercicio.banco.repository.UserRepository;

@Service
public class ContaCorrenteService {
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ContaRepository contaRepository;

	public Conta criarConta(CadastroBean cad) throws CpfExistenteException, EmailExistenteException {
		// 1 - Não deixa mais que 1 CPF na base de clientes.
		Usuario u = repository.findByCpf(cad.getCpf());
		if (u != null) throw new CpfExistenteException();

		// 2 - Apenas 1 email na base de clientes.
		u = repository.findByEmail(cad.getEmail());
		if (u != null) throw new EmailExistenteException();

		u = new Usuario();
		u.setNome(cad.getNome());
		u.setEmail(cad.getEmail());
		// TODO CRIPTOGRAFAR
		u.setSenha(cad.getSenha());

		repository.save(u);
		Conta c = new Conta();
		c.setUser(u);
		
		return contaRepository.save(c);
	}
}
