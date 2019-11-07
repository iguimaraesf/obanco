package com.exercicio.banco.controller;


public class DepositoBeanDoTeste {

	@javax.validation.constraints.NotNull
	private Long conta;

	public Long getConta() {
		return conta;
	}
	public void setConta(Long contaDestino) {
		this.conta = contaDestino;
	}
	
	
}
