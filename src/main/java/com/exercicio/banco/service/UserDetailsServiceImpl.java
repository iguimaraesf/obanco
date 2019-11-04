package com.exercicio.banco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exercicio.banco.domain.Correntista;
import com.exercicio.banco.repository.CorrentistaRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CorrentistaRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Correntista u = repository.findByEmail(username);
		if (u == null) throw new UsernameNotFoundException("Correntista inexistente.");
		UserDetailsImpl principal = new UserDetailsImpl(u);
		return principal;
	}

}
