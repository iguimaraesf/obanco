package com.exercicio.banco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exercicio.banco.domain.Correntista;
import com.exercicio.banco.repository.CorrentistaRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CorrentistaRepository repository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Correntista u = repository.findByEmail(username);
		if (u == null) throw new UsernameNotFoundException("Correntista inexistente.");
		//System.out.println("SERVICE - " + u.getPapel());
		UserDetailsImpl principal = new UserDetailsImpl(u);
		return principal;
	}

}
