package com.exercicio.banco.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/extrato/{conta}")
	public List<HistoricoBean> extrato(@PathVariable final Long conta) throws Exception {
		return service.extrato(conta);
	}
	@PostMapping("/depositar")
	@ResponseStatus(code = HttpStatus.CREATED)
	@Validated
	public SaldoBean depositar(@RequestBody @Valid final DepositoBean deposito) throws Exception {
		return service.depositar(deposito);
	}
	@PostMapping("/transferir")
	public SaldoBean transferir(@RequestBody @Valid final TransferenciaBean transf) throws Exception {
		return service.transferir(transf);
	}

}
