package com.exercicio.banco.domain;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Correntista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@NotNull
	@NotEmpty
	@Column(length = 200)
	private String nome;

	@JsonIgnore
	@NotNull
	@NotEmpty
	@Column(length = 60)
	private String senha;

	@NotNull
	@NotEmpty
	@Email
	@Column(length = 255)
	private String email;
	
	@NotNull
	@Column(unique = true)
	private Long cpf;
	
	@NotNull
	@Column
	private Boolean ativo;
	
	@ManyToMany
	/*@JoinTable(joinColumns = @JoinColumn(name = "correntista_id"), 
		inverseJoinColumns = @JoinColumn(name = "papel_id"))*/
	private Set<Papel> papel;
	
	@ManyToMany
	private Set<Conta> conta;
	
	public Correntista() {
		super();
		this.ativo = true;
	}
	public Correntista(@NotNull @NotEmpty String nome, @NotNull @NotEmpty String senha, @NotNull @NotEmpty @Email String email,
			@NotNull @CPF Long cpf) {
		this();
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public Set<Papel> getPapel() {
		return papel;
	}
	public void setPapel(Set<Papel> roles) {
		this.papel = roles;
	}
	
	public Set<Conta> getConta() {
		return conta;
	}
	public void setConta(Set<Conta> contas) {
		this.conta = contas;
	}
	
	public Long getCpf() {
		return cpf;
	}
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	@Override
	public int hashCode() {
		return Objects.hash(email);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Correntista)) {
			return false;
		}
		Correntista other = (Correntista) obj;
		return Objects.equals(email, other.email);
	}
	
	
}
