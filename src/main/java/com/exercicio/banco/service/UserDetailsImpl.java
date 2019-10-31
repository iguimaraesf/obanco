package com.exercicio.banco.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.exercicio.banco.domain.Usuario;

public class UserDetailsImpl implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8280465498472355525L;
	private Usuario usuario;
	
	public UserDetailsImpl(Usuario usuario) {
		this.usuario = usuario;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auth = new ArrayList<>();
		this.usuario.getRoles().forEach(role -> {
			GrantedAuthority a1 = new SimpleGrantedAuthority("ROLE_" + role.getName());
			auth.add(a1);
		});
		return auth;
	}

	@Override
	public String getPassword() {
		return this.usuario.getSenha();
	}

	@Override
	public String getUsername() {
		return this.usuario.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.usuario.getAtivo();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.usuario.getAtivo();
	}

}
