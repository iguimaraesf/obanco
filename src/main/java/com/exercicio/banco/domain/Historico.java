package com.exercicio.banco.domain;

import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.sun.istack.NotNull;

@Entity
public class Historico {
	
	@EmbeddedId
	@NotNull
	private HistoricoPK id;
	@NotNull
	private BigDecimal valor;
	private String descricao;
	
	public Historico() {
		
	}

	public Historico(HistoricoPK key, BigDecimal valor, String descricao) {
		this();
		this.id = key;
		this.valor = valor;
		this.descricao = descricao;
	}

	public HistoricoPK getId() {
		return id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setId(HistoricoPK id) {
		this.id = id;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


}
