package com.exercicio.banco.service;

import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.exercicio.banco.controller.DepositoBean;
import com.exercicio.banco.controller.SaldoBean;
import com.exercicio.banco.domain.Conta;
import com.exercicio.banco.domain.Correntista;
import com.exercicio.banco.domain.Historico;
import com.exercicio.banco.domain.HistoricoPK;
import com.exercicio.banco.exception.ContaInexistenteException;
import com.exercicio.banco.exception.SemSecaoException;
import com.exercicio.banco.repository.ContaRepository;
import com.exercicio.banco.repository.CorrentistaRepository;
import com.exercicio.banco.repository.HistoricoRepository;

@Service
public class ContaCorrenteService {

	@Autowired
	private ContaRepository repositoryConta;
	@Autowired
	private HistoricoRepository repositoryHistorico;
	@Autowired
	private CorrentistaRepository repositoryCorrentista;
	@Autowired
	private EntityManager entityManager;

	public SaldoBean depositar(DepositoBean deposito) throws SemSecaoException, ContaInexistenteException {
		UserDetails correntista = this.correntistaAtual();
		// contas
		Optional<Conta> contaD = repositoryConta.findById(deposito.getContaDestino());
		if (contaD.isEmpty()) throw new ContaInexistenteException(deposito.getContaDestino());
		
		Historico historico = new Historico(new HistoricoPK(contaD.get()), deposito.getValor(), 
				"Depósito cartão " + deposito.getCartaoCredito().substring(0, 4)
				+ " feito por " + correntista.getUsername());
		repositoryHistorico.save(historico);

		return this.obterSaldo(deposito.getContaDestino());
	}

	private SaldoBean obterSaldo(Long numeroConta) {
		String ql = "SELECT SUM(a.valor) FROM Historico a WHERE a.id.conta.id = :numero";
		Query q = entityManager.createQuery(ql).setParameter("numero", numeroConta);
		BigDecimal result = (BigDecimal) q.getSingleResult();
		SaldoBean saldo = new SaldoBean(numeroConta, result);
		return saldo;
	}

	private UserDetails correntistaAtual() throws SemSecaoException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return (UserDetails)principal;
		} else if (principal instanceof String) {
			Correntista c = repositoryCorrentista.findByEmail(String.valueOf(principal));
			if (c != null) return new UserDetailsImpl(c);
		}
		throw new SemSecaoException();
	}
}
