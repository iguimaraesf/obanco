package com.exercicio.banco.controller;

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
import com.exercicio.banco.service.UsuarioService;

@RestController
@RequestMapping("api/v1/onboarding")
public class OnBoardingController {
	@Autowired
	private UsuarioService service;
	
	@PostMapping("/entrar")
	@ResponseBody
	public String entrar(@RequestBody AccountCredentials login) {
		return "{\"entrar\":\"ok\"}";
	}
	@PostMapping("/criar")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String criar(@RequestBody CadastroBean cad) {
		return "ok - criar";
	}
	@GetMapping("/teste")
	public String teste() {
		return "teste";
	}
}
