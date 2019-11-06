package com.exercicio.banco.validacao;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ValidacaoDeSenhas {

	private static final String SENHA_VALIDO1 = "Teste123.4";
	private static final String SENHA_VALIDO2 = "123.45Teste";

	private static Validator validator;
	
	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
	}
	
	@Test
	public void senhaDiferente() {
		CampoSenhaOk cad = new CampoSenhaOk();
		cad.setSenha(SENHA_VALIDO1);
		cad.setConfirmacaoSenha(SENHA_VALIDO2);
		Set<ConstraintViolation<CampoSenhaOk>> res = validator.validate(cad);
		Assertions.assertEquals(1, res.size());
		
		Iterator<ConstraintViolation<CampoSenhaOk>> it = res.iterator();
		ConstraintViolation<CampoSenhaOk> c1 = it.next();
		
		Assertions.assertEquals("Os campos devem ser iguais.", c1.getMessage());
		Assertions.assertEquals("senha", c1.getPropertyPath().toString());
	}

	@Test
	public void senhaIgual() {
		CampoSenhaOk cad = new CampoSenhaOk();
		cad.setSenha(SENHA_VALIDO1);
		cad.setConfirmacaoSenha(SENHA_VALIDO1);
		Set<ConstraintViolation<CampoSenhaOk>> res = validator.validate(cad);
		Assertions.assertEquals(0, res.size());
	}

	@Test
	public void campoUmcampoNaoEncontrado() {
		CampoSenhaInvalido cad = new CampoSenhaInvalido();
		cad.setSenha(SENHA_VALIDO1);
		cad.setConfirmacao(SENHA_VALIDO2);
		
		Set<ConstraintViolation<CampoSenhaInvalido>> res = validator.validate(cad);
		Assertions.assertEquals(1, res.size());
		
		Iterator<ConstraintViolation<CampoSenhaInvalido>> it = res.iterator();
		ConstraintViolation<CampoSenhaInvalido> c1 = it.next();
		
		Assertions.assertEquals("umcampo", c1.getPropertyPath().toString());
		Assertions.assertEquals("Campo umcampo não encontrado na classe com.exercicio.banco.validacao.CampoSenhaInvalido", c1.getMessage());
	}

	@Test
	public void campoConfirmacaoDaSenhaNaoEncontrado() {
		CampoConfirmacaoSenhaInvalido cad = new CampoConfirmacaoSenhaInvalido();
		cad.setSenha(SENHA_VALIDO1);
		cad.setConfirmacao(SENHA_VALIDO2);
		
		Set<ConstraintViolation<CampoConfirmacaoSenhaInvalido>> res = validator.validate(cad);
		Assertions.assertEquals(1, res.size());
		
		Iterator<ConstraintViolation<CampoConfirmacaoSenhaInvalido>> it = res.iterator();
		ConstraintViolation<CampoConfirmacaoSenhaInvalido> c1 = it.next();
		
		Assertions.assertEquals("confirmacaoDaSenha", c1.getPropertyPath().toString());
		Assertions.assertEquals("Campo confirmacaoDaSenha não encontrado na classe com.exercicio.banco.validacao.CampoConfirmacaoSenhaInvalido", c1.getMessage());
	}
}
