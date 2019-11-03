package com.exercicio.banco.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.exercicio.banco.domain.Correntista;

public class UserDetailsImpl implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8280465498472355525L;
	private Correntista correntista;
	
	public UserDetailsImpl(Correntista usuario) {
		this.correntista = usuario;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auth = new ArrayList<>();
		this.correntista.getRoles().forEach(role -> {
			GrantedAuthority a1 = new SimpleGrantedAuthority("ROLE_" + role.getName());
			auth.add(a1);
		});
		return auth;
	}

	@Override
	public String getPassword() {
		return this.correntista.getSenha();
	}

	@Override
	public String getUsername() {
		return this.correntista.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.correntista.getAtivo();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.correntista.getAtivo();
	}

}
