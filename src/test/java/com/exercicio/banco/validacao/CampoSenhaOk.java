package com.exercicio.banco.validacao;

@CamposIguais(primeiro = "senha", segundo = "confirmacaoSenha")
public class CampoSenhaOk {
	private String senha;
	private String confirmacaoSenha;
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
