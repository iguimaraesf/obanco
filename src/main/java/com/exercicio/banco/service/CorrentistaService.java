package com.exercicio.banco.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exercicio.banco.domain.Conta;
import com.exercicio.banco.domain.Correntista;
import com.exercicio.banco.domain.Papel;
import com.exercicio.banco.repository.ContaRepository;
import com.exercicio.banco.repository.CorrentistaRepository;
import com.exercicio.banco.repository.PapelRepository;

@Service
public class CorrentistaService {

	@Autowired
	private CorrentistaRepository repository;
	@Autowired
	private PapelRepository rRepo;
	@Autowired
	private ContaRepository rConta;

	public Iterable<Correntista> listarTodos() {
		return this.repository.findAll();
	}

	public Correntista findByEmail(String email) {
		return this.repository.findByEmail(email);
	}
	
	public Correntista criarCorrentistaEConta(Correntista correntistaParam) {
		Correntista correntista = repository.findByCpf(correntistaParam.getCpf());
		if (correntista == null) {
			correntista = new Correntista();
			correntista.setConta(new HashSet<>());
		}
		correntista.setNome(correntistaParam.getNome());
		correntista.setCpf(correntistaParam.getCpf());
		correntista.setEmail(correntistaParam.getEmail());
		correntista.setSenha(this.encoder().encode(correntistaParam.getSenha()));
		correntista.setPapel(new HashSet<>());
		// apenas papel de usuário
		Optional<Papel> souUsuario = rRepo.findById(1L);
		if (souUsuario.isPresent()) {
			Papel r = souUsuario.get();
			correntista.getPapel().add(r);
		}
		Conta conta = new Conta();
		rConta.save(conta);
		Set<Conta> contas = correntista.getConta();
		contas.add(conta);
		//rConta.save(conta);
		
		return repository.save(correntista);
	}
	
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	public void excluir(Long id) {
		final Optional<Correntista> c = this.repository.findById(id);
		c.ifPresent(corr -> {
			this.repository.delete(corr);
		});
	}

	public Correntista ativar(Long id) {
		return mudarFlagAtivo(id, true);
	}

	public Correntista desativar(Long id) {
		return mudarFlagAtivo(id, false);
	}

	private Correntista mudarFlagAtivo(Long id, boolean valor) {
		final Optional<Correntista> c = this.repository.findById(id);
		c.ifPresent(corr -> {
			corr.setAtivo(valor);
			this.repository.save(corr);
		});
		if (c.isEmpty()) return null;
		return c.get();
	}

}
