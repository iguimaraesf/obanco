package com.exercicio.banco.domain;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	@Column(length = 255)
	private String email;
	
	@NotNull
	@NotEmpty
	@Column(unique = true)
	private Long cpf;
	
	@NotNull
	@Column
	private Boolean ativo;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Role> roles;
	
	@OneToMany(orphanRemoval = true, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	//@JoinColumn(name = "id", referencedColumnName = "user_id")
	private Set<Conta> contas;
	
	public Correntista() {
		super();
	}
	public Correntista(@NotNull @NotEmpty String nome, @NotNull @NotEmpty String senha, @NotNull @NotEmpty String email,
			@NotNull @NotEmpty Long cpf) {
		this();
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.cpf = cpf;
		this.ativo = true;
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
	
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public Set<Conta> getContas() {
		return contas;
	}
	public void setContas(Set<Conta> contas) {
		this.contas = contas;
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
