package com.exercicio.banco.controller;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.exercicio.banco.validacao.CamposIguais;

@CamposIguais(primeiro = "senha", segundo = "confirmacaoSenha")
public class PerfilBean {
	@Length(min = 1, max = 200)
	private String nome;
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[-!$%^&*()_+|~=`{}\\[\\]:\";'<>?,.\\/]).{8,}$", message = "Pelo menos 8 caracteres, com letras e números e símbolos")
	private String senha;
	private String confirmacaoSenha;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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

}
