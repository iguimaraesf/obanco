package com.exercicio.banco.validacao;

@CamposIguais(primeiro = "senha", segundo = "confirmacaoDaSenha")
public class CampoConfirmacaoSenhaInvalido {
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
