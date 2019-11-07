package com.exercicio.banco.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exercicio.banco.controller.DepositoBean;
import com.exercicio.banco.controller.HistoricoBean;
import com.exercicio.banco.controller.SaldoBean;
import com.exercicio.banco.controller.TransferenciaBean;
import com.exercicio.banco.domain.Conta;
import com.exercicio.banco.domain.Correntista;
import com.exercicio.banco.domain.Historico;
import com.exercicio.banco.domain.HistoricoPK;
import com.exercicio.banco.exception.BancoException;
import com.exercicio.banco.exception.ContaInexistenteException;
import com.exercicio.banco.exception.ContaSemAcessoException;
import com.exercicio.banco.exception.SaldoInsuficienteException;
import com.exercicio.banco.repository.ContaRepository;
import com.exercicio.banco.repository.HistoricoRepository;
import com.exercicio.banco.util.DadosGlobais;

@Service
public class ContaCorrenteService {

	@Autowired
	private ContaRepository repositoryConta;
	@Autowired
	private HistoricoRepository repositoryHistorico;
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private DadosGlobais dadosGlobais;

	@Transactional
	public SaldoBean depositar(DepositoBean deposito) throws BancoException {
		Correntista correntista = dadosGlobais.correntistaAtual();
		// contas
		Conta contaD = this.obterConta(deposito.getContaDestino());
		
		Historico historico = new Historico(new HistoricoPK(contaD), deposito.getValor(), 
				"Depósito cartão " + deposito.getCartaoCredito().substring(0, 4)
				+ " feito por " + correntista.getNome());
		repositoryHistorico.save(historico);

		return this.obterSaldo(correntista, deposito.getContaDestino());
	}

	@Transactional
	public SaldoBean transferir(@Valid TransferenciaBean transf) throws BancoException {
		Correntista correntista = dadosGlobais.correntistaAtual();
		// contas
		Conta contaD = this.obterConta(transf.getContaDestino());
		Conta contaO = this.obterConta(transf.getContaOrigem());
		// A conta de origem tem que pertencer à pessoa autenticada.
		boolean existe = correntista.getConta().stream().anyMatch(c -> c.getId().equals(contaO.getId()));
		if (!existe) throw new ContaSemAcessoException();
		// Valor tem que ser menor que o saldo.
		SaldoBean saldoAtual = obterSaldo(correntista, contaO.getId());
		if (saldoAtual.getSaldo().compareTo(transf.getValor())< 0) throw new SaldoInsuficienteException();
		// Retira da conta de origem
		Historico ho = new Historico(new HistoricoPK(contaO), new BigDecimal(-transf.getValor().doubleValue()), transf.getDescricao());
		repositoryHistorico.save(ho);
		// Deposita na conta de destino
		Historico hd = new Historico(new HistoricoPK(contaD), transf.getValor(), transf.getDescricao());
		repositoryHistorico.save(hd);
		// Retorna o saldo
		return this.obterSaldo(correntista, contaO.getId());
	}

	public List<HistoricoBean> extrato(Long conta) throws BancoException {
		Correntista correntista = dadosGlobais.correntistaAtual();
		// A conta de origem tem que pertencer à pessoa autenticada.
		boolean existe = correntista.getConta().stream().anyMatch(c -> c.getId().equals(conta));
		if (!existe) throw new ContaSemAcessoException();
		// extrato
		List<Historico> lista = repositoryHistorico.findByIdContaId(conta);
		List<HistoricoBean> res = lista.stream().map(hist -> new HistoricoBean(hist.getId().getHora(), hist.getValor(), hist.getDescricao())).collect(Collectors.toList());
		return res;
	}

	private Conta obterConta(Long conta) throws ContaInexistenteException {
		Optional<Conta> contaD = repositoryConta.findById(conta);
		if (contaD.isEmpty()) throw new ContaInexistenteException(conta);
		return contaD.get();
	}

	private SaldoBean obterSaldo(Correntista correntista, Long contaDep) {
		Optional<Conta> opt = this.contaDoSaldo(correntista.getConta(), contaDep);
		Optional<Conta> def = correntista.getConta().stream().findFirst();
		Long contaDef = def.isEmpty() ? null : def.get().getId();
		Long numeroConta = opt.isPresent() ? opt.get().getId() : contaDef;
		String ql = "SELECT SUM(a.valor) FROM Historico a WHERE a.id.conta.id = :numero";
		Query q = entityManager.createQuery(ql).setParameter("numero", numeroConta);
		BigDecimal result = (BigDecimal) q.getSingleResult();
		SaldoBean saldo = new SaldoBean(numeroConta, result);
		return saldo;
	}

	private Optional<Conta> contaDoSaldo(Set<Conta> set, Long contaDep) {
		return set.stream().filter(c -> c.getId().equals(contaDep)).findAny();
	}

}
