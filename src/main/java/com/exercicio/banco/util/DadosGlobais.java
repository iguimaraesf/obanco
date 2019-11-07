package com.exercicio.banco.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.exercicio.banco.domain.Correntista;
import com.exercicio.banco.exception.SemSecaoException;
import com.exercicio.banco.repository.CorrentistaRepository;

@Component
public class DadosGlobais {
	@Autowired
	private CorrentistaRepository repositoryCorrentista;

	public Correntista correntistaAtual() throws SemSecaoException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = "";
		if (principal instanceof UserDetails) {
			email = ((UserDetails)principal).getUsername();
		} else if (principal instanceof String) {
			email = String.valueOf(principal);
		} else {
			throw new SemSecaoException();
		}
		return repositoryCorrentista.findByEmail(email);
	}
}
