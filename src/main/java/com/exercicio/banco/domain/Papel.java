package com.exercicio.banco.domain;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "papel")
@Table(name = "papel")
public class Papel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotNull
	@NotEmpty
	@Column(length = 30)
    private String name;

	@ManyToMany(mappedBy = "papel")
    @JsonBackReference
    private Set<Correntista> correntista;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Correntista> getCorrentista() {
        return correntista;
    }

    public void setCorrentista(Set<Correntista> users) {
        this.correntista = users;
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
		if (!(obj instanceof Papel)) {
			return false;
		}
		Papel other = (Papel) obj;
		return Objects.equals(id, other.id);
	}
    
    
}
