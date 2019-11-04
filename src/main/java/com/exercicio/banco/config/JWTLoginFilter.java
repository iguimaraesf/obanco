package com.exercicio.banco.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
	private UserDetailsService service;

	protected JWTLoginFilter(String url, AuthenticationManager manager, UserDetailsService service) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(manager);
		this.service = service;
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException {
		
		AccountCredentials credentials = new ObjectMapper()
				.readValue(request.getInputStream(), AccountCredentials.class);
		
		UserDetails details = service.loadUserByUsername(credentials.getEmail());
		if (details == null ) throw new UsernameNotFoundException("E-mail " + credentials.getEmail() + " não cadastrado.");
		
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
						credentials.getEmail(), 
						credentials.getSenha(),
						details.getAuthorities()
						)
				);
	}
	
	@Override
	protected void successfulAuthentication(
			HttpServletRequest request, 
			HttpServletResponse response,
			FilterChain filterChain,
			Authentication auth) throws IOException, ServletException {
		
		TokenAuthenticationService.addAuthentication(response, auth.getName(), auth.getAuthorities());
		//filterChain.doFilter(request, response);
	}
	/*@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		super.unsuccessfulAuthentication(request, response, failed);
		throw new ServletException("Falha na autenticação", failed);
	}*/
	
	

}
