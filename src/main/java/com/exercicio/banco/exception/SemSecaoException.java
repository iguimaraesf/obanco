package com.exercicio.banco.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NETWORK_AUTHENTICATION_REQUIRED, reason = "Sua sessão expirou.")
public class SemSecaoException extends BancoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6788405843629891482L;

}
