package com.exercicio.banco.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class HistoricoBean {
	private final LocalDateTime hora;
	private final BigDecimal valor;
	private final String descricao;
	public HistoricoBean(LocalDateTime hora, BigDecimal valor, String descricao) {
		super();
		this.hora = hora;
		this.valor = valor;
		this.descricao = descricao;
	}
	public LocalDateTime getHora() {
		return hora;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public String getDescricao() {
		return descricao;
	}
	
}
