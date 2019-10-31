package com.exercicio.banco.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.exercicio.banco.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
//@ConfigurationProperties(prefix = "jwt.config")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl service;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/api/v1/onboarding/**").permitAll()
			//.anyRequest().authenticated()
			.and()
			.authorizeRequests()
			.antMatchers("/api/v1/conta/**").hasRole("USER")
			.and()
			.authorizeRequests()
			.antMatchers("/api/v1/admin/**").hasRole("ADMIN")
			// filtro para login
			.and()
			.addFilterBefore(new JWTLoginFilter("/api/v1/onboarding/entrar", super.authenticationManager(), service), UsernamePasswordAuthenticationFilter.class)
			// verifica JWT
			.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
		;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(this.authenticationProvider());
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(this.criptografiaDaAplicacao());
		provider.setUserDetailsService(this.service);
		return provider;
	}

	@Bean
	PasswordEncoder criptografiaDaAplicacao() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
