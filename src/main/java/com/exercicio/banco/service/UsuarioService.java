package com.exercicio.banco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercicio.banco.domain.Usuario;
import com.exercicio.banco.repository.UserRepository;

@Service
public class UsuarioService {

	@Autowired
	private UserRepository repository;

	public List<Usuario> listarTodos() {
		return this.repository.findAll();
	}

	public Usuario findByEmail(String email) {
		return this.repository.findByEmail(email);
	}
}
