package com.exercicio.banco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exercicio.banco.domain.Usuario;
import com.exercicio.banco.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
	@Autowired
	private UsuarioService service;

	@GetMapping("/usuarios")
	@ResponseBody
	public List<Usuario> listarUsuarios() {
		return service.listarTodos();
	}
	@PatchMapping("/usuarios")
	public String ativarDesativarUsuarios() {
		return "ativarDesativarUsuarios";
	}

}
