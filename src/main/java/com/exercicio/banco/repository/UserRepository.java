package com.exercicio.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercicio.banco.domain.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long> {
	public Usuario findByCpf(Long cpf);

	public Usuario findByEmail(String email);
}
