package com.exercicio.banco.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED, reason = "Conta corrente inexistente.")
public class ContaInexistenteException extends BancoException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2253523210910726783L;
	private final Long conta;
	public ContaInexistenteException(Long conta) {
		super();
		this.conta = conta;
	}
	public Long getConta() {
		return conta;
	}
	
}
