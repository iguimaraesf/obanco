package com.exercicio.banco.validacao;

@CamposIguais(primeiro = "umcampo", segundo = "confirmacao")
public class CampoSenhaInvalido {
	private String senha;
	private String confirmacao;
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getConfirmacao() {
		return confirmacao;
	}
	public void setConfirmacao(String confirmacao) {
		this.confirmacao = confirmacao;
	}
	
}
