package com.exercicio.banco.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.exercicio.banco.service.ContaCorrenteService;

@RestController
@RequestMapping("/api/v1/conta")
public class ContaController {
	@Autowired
	private ContaCorrenteService service;

	@GetMapping("/extrato")
	public String extrato() {
		return "ok - extrato";
	}
	@PostMapping("/depositar")
	@ResponseStatus(code = HttpStatus.CREATED)
	@Validated
	public SaldoBean depositar(@Valid @RequestBody final DepositoBean deposito) throws Exception {
		return service.depositar(deposito);
	}
	@PostMapping("/transferir")
	public String transferir() {
		return "Transferir";
	}
	@PatchMapping("/editar")
	public String editar() {
		return "editar";
	}

}
