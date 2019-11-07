package com.exercicio.banco.domain;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn
	@JsonBackReference
	private Correntista correntista;
	
	@OneToMany(mappedBy = "id.conta")
	private Set<Historico> historico;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Correntista getCorrentista() {
		return correntista;
	}
	public void setCorrentista(Correntista user) {
		this.correntista = user;
	}
	public Set<Historico> getHistorico() {
		return historico;
	}
	public void setHistorico(Set<Historico> historico) {
		this.historico = historico;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Conta)) {
			return false;
		}
		Conta other = (Conta) obj;
		return Objects.equals(id, other.id);
	}
	
}
