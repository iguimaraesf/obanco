package com.exercicio.banco.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/conta")
public class ContaController {
	@GetMapping("/extrato")
	public String extrato() {
		return "ok - extrato";
	}
	@GetMapping("/depositar")
	public String depositar() {
		return "depositar";
	}
	@PostMapping("/transferir")
	public String transferir() {
		return "Transferir";
	}
	@PostMapping("/editar")
	public String editar() {
		return "editar";
	}

}
