package com.exercicio.banco.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;

@Embeddable
public class HistoricoPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6361821041235823314L;
	
	@NotNull
	private LocalDateTime hora;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "conta_id", referencedColumnName = "id")
	@JsonBackReference
	private Conta conta;

	public HistoricoPK() {
		
	}
	public HistoricoPK(LocalDateTime hora, Conta conta) {
		this();
		this.hora = hora;
		this.conta = conta;
	}
	
	public HistoricoPK(Conta conta) {
		this(LocalDateTime.now(), conta);
	}

	public LocalDateTime getHora() {
		return hora;
	}
	public void setHora(LocalDateTime dataHora) {
		this.hora = dataHora;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta agenciaConta) {
		this.conta = agenciaConta;
	}
	@Override
	public int hashCode() {
		return Objects.hash(conta, hora);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof HistoricoPK)) {
			return false;
		}
		HistoricoPK other = (HistoricoPK) obj;
		return Objects.equals(conta, other.conta) && Objects.equals(hora, other.hora);
	}
	
	
	
}
