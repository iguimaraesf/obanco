package com.exercicio.banco.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class HistoricoPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2922567694894075955L;

	@Column(name = "datahora")
	private LocalDateTime dataHora;
	@Column(name = "conta")
	private Conta conta;

	public LocalDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta agenciaConta) {
		this.conta = agenciaConta;
	}
	@Override
	public int hashCode() {
		return Objects.hash(conta, dataHora);
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
		return Objects.equals(conta, other.conta) && Objects.equals(dataHora, other.dataHora);
	}
	
	
	
}
