package com.exercicio.banco.controller;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class TransferenciaBean {
	@NotNull
	private Long contaDestino;
	@NotNull
	private Long contaOrigem;
	@DecimalMin(value = "0.01")
	@NotNull
	private BigDecimal valor;
	@Length(min = 0, max = 255)
	private String descricao;
	public Long getContaDestino() {
		return contaDestino;
	}
	public void setContaDestino(Long contaDestino) {
		this.contaDestino = contaDestino;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Long getContaOrigem() {
		return contaOrigem;
	}
	public void setContaOrigem(Long contaOrigem) {
		this.contaOrigem = contaOrigem;
	}
	
}
