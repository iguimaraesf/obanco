package com.exercicio.banco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exercicio.banco.domain.Correntista;
import com.exercicio.banco.service.CorrentistaService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
	@Autowired
	private CorrentistaService service;

	@GetMapping("/correntistas")
	@ResponseBody
	public Iterable<Correntista> listarCorrentistas() {
		return service.listarTodos();
	}
	@PatchMapping("/correntistas/ativar/{id}")
	public Correntista ativar(@PathVariable Long id) {
		return service.ativar(id);
	}
	@PatchMapping("/correntistas/desativar/{id}")
	public Correntista desativar(@PathVariable Long id) {
		return service.desativar(id);
	}
	@DeleteMapping("/correntistas/{id}")
	public void excluir(@PathVariable Long id) {
		service.excluir(id);
	}
}
