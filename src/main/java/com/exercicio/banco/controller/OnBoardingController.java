package com.exercicio.banco.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.exercicio.banco.config.AccountCredentials;
import com.exercicio.banco.domain.Correntista;
import com.exercicio.banco.service.CorrentistaService;

@RestController
@RequestMapping("api/v1/onboarding")
public class OnBoardingController {
	@Autowired
	private CorrentistaService service;
	
	@PostMapping("/entrar")
	@ResponseBody
	public String entrar(@RequestBody AccountCredentials login) {
		return "{\"entrar\":\"ok\"}";
	}

	@PostMapping("/criar")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Correntista criar(@RequestBody @Valid CadastroBean cad) {
		Correntista corr = new Correntista();
		corr.setAtivo(true);
		corr.setCpf(Long.valueOf(cad.getCpf()));
		corr.setEmail(cad.getEmail());
		corr.setNome(cad.getNome());
		corr.setSenha(cad.getSenha());
		return service.criarCorrentistaEConta(corr);
	}

	@GetMapping("/teste")
	public String teste() {
		return "teste";
	}
}
