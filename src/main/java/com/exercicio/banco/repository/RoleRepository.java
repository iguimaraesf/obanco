package com.exercicio.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercicio.banco.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
}
