package com.exercicio.banco.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {
	// EXPIRATION_TIME = 10 dias
	static final long EXPIRATION_TIME = 860_000_000;
	static final String SECRET = "MySecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	
	static void addAuthentication(HttpServletResponse response, String username, Collection<? extends GrantedAuthority> auth) {
		String JWT = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.claim("auth", auth)
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
		
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	}
	
	static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		
		if (token != null) {
			// faz parse do token
			Claims claims = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody();
			String user = claims.getSubject();
			@SuppressWarnings("unchecked")
			Collection<Map<String, String>> lista = (Collection<Map<String, String>>) claims.get("auth");
			Collection<? extends GrantedAuthority> auth = toAuthorization(lista);
			
			if (user != null) {
				AccountCredentials cred = new AccountCredentials();
				cred.setEmail(user);
				return new UsernamePasswordAuthenticationToken(user, cred, auth);
			}
		}
		return null;
	}

	private static Collection<? extends GrantedAuthority> toAuthorization(Collection<Map<String, String>> lista) {
		List<GrantedAuthority> auth = new ArrayList<>();
		lista.forEach(role -> {
			GrantedAuthority a1 = new SimpleGrantedAuthority(role.get("authority"));
			auth.add(a1);
		});
		return auth;
	}

}
