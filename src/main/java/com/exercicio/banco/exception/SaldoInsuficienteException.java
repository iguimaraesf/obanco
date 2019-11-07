package com.exercicio.banco.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED, reason = "Saldo insuficiente.")
public class SaldoInsuficienteException extends BancoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 861911729469074415L;

}
