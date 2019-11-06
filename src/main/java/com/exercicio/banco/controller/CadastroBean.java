package com.exercicio.banco.controller;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;

import com.exercicio.banco.validacao.CamposIguais;

@CamposIguais(primeiro = "senha", segundo = "confirmacaoSenha")
public class CadastroBean {
	@NotEmpty
	private String nome;
	@NotEmpty
	@Email
	private String email;
	@NotEmpty
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[-!$%^&*()_+|~=`{}\\[\\]:\";'<>?,.\\/]).{8,}$", message = "Pelo menos 8 caracteres, com letras e números e símbolos")
	private String senha;
	@NotEmpty
	private String confirmacaoSenha;
	@CPF
	@NotNull
	@NotEmpty
	private String cpf;
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
	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}
	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	
}
