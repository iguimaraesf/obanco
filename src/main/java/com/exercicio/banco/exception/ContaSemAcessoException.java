package com.exercicio.banco.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "Conta corrente sem acesso.")
public class ContaSemAcessoException extends BancoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -610579504349061927L;

}
