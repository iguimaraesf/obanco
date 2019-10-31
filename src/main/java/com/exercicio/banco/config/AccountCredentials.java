package com.exercicio.banco.config;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class AccountCredentials {
	@Email
	@NotEmpty
	private String email;
	private String senha;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
