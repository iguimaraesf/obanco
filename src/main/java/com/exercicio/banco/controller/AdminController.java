package com.exercicio.banco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
	@PatchMapping("/correntistas")
	public String ativarDesativar() {
		return "ativarDesativar";
	}

}
