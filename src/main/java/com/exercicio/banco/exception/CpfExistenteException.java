package com.exercicio.banco.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Este CPF já está cadastrado.")
public class CpfExistenteException extends BancoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7593193363603209669L;
	
}
