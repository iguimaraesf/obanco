package com.exercicio.banco.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Este e-mail já está cadastrado.")
public class EmailExistenteException extends BancoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2935144957106189669L;
	
}
