package com.exercicio.banco.controller;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.exercicio.banco.controller.CadastroBean;

public class ValidacaoDeCorrentista {
	private static final String CAMPO_CPF = "cpf";
	private static final String CAMPO_NOME = "nome";
	private static final String CAMPO_EMAIL = "email";
	private static final String CAMPO_SENHA = "senha";
	private static final String MSG_SENHA = "Pelo menos 8 caracteres, com letras e números e símbolos";
	private static final String SENHA_INVALIDO1_CURTA                   = "123!a";
	private static final String SENHA_INVALIDO2_SEM_LETRAS              = "12345678.";
	private static final String SENHA_INVALIDO3_SEM_NUMEROS             = "abcdefgh.";
	private static final String SENHA_INVALIDO4_SEM_SIMBOLOS            = "abcdefgh1";
	private static final String SENHA_INVALIDO5_SEM_SIMBOLOS_LETRAS     = "123456789";
	private static final String SENHA_INVALIDO6_SEM_SIMBOLOS_NUMEROS    = "UMA SENHA grande";
	private static final String CPF_VALIDO1                             = "32068361078";
	private static final String CPF_INVALIDO1                           = "12345";
	private static final String EMAIL_VALIDO1                           = "email@email.com";
	private static final String EMAIL_INVALIDO1                         = "email.WWW.com";
	private static final String NOME_VALIDO1                            = "Nome Válido";
	private static final String SENHA_VALIDO1                           = "Teste123.4";
	private static final String SENHA_VALIDO2                           = "123.45Teste";

	private static Validator validator;
	
	private CadastroBean beanValido() {
		CadastroBean cad = new CadastroBean();
		cad.setNome(NOME_VALIDO1);
		cad.setEmail(EMAIL_VALIDO1);
		cad.setCpf(CPF_VALIDO1);
		cad.setSenha(SENHA_VALIDO1);
		cad.setConfirmacaoSenha(SENHA_VALIDO1);
		return cad;
	}
	
	private void temMensagem(Set<ConstraintViolation<CadastroBean>> res, String campo, String mensagem) {
		Iterator<ConstraintViolation<CadastroBean>> it = res.iterator();
		String campoComp = "";
		String mensComp = "";
		while (it.hasNext()) {
			ConstraintViolation<CadastroBean> c1 = it.next();
			if (campo.equals(c1.getPropertyPath().toString())) {
				campoComp = campo;
				if (mensagem.equals(c1.getMessage())) {
					mensComp = mensagem;
				}
			}
		}
		Assertions.assertEquals(campo, campoComp);
		Assertions.assertEquals(mensagem, mensComp);
	}

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
	}

	@Test
	public void senhaMuitoCurta() {
		CadastroBean cad = beanValido();
		cad.setSenha(SENHA_INVALIDO1_CURTA);
		cad.setConfirmacaoSenha(SENHA_INVALIDO1_CURTA);
		Set<ConstraintViolation<CadastroBean>> res = validator.validate(cad);
		//System.out.println(res);
		Assertions.assertEquals(1, res.size());

		temMensagem(res, CAMPO_SENHA, MSG_SENHA);
	}

	@Test
	public void senhaNaoContemLetras() {
		CadastroBean cad = beanValido();
		cad.setSenha(SENHA_INVALIDO2_SEM_LETRAS);
		cad.setConfirmacaoSenha(SENHA_INVALIDO2_SEM_LETRAS);
		Set<ConstraintViolation<CadastroBean>> res = validator.validate(cad);
		//System.out.println(res);
		Assertions.assertEquals(1, res.size());

		temMensagem(res, CAMPO_SENHA, MSG_SENHA);
	}

	@Test
	public void senhaNaoContemNumeros() {
		CadastroBean cad = beanValido();
		cad.setSenha(SENHA_INVALIDO3_SEM_NUMEROS);
		cad.setConfirmacaoSenha(SENHA_INVALIDO3_SEM_NUMEROS);
		Set<ConstraintViolation<CadastroBean>> res = validator.validate(cad);
		//System.out.println(res);
		Assertions.assertEquals(1, res.size());

		temMensagem(res, CAMPO_SENHA, MSG_SENHA);
	}

	@Test
	public void senhaNaoContemSimbolos() {
		CadastroBean cad = beanValido();
		cad.setSenha(SENHA_INVALIDO4_SEM_SIMBOLOS);
		cad.setConfirmacaoSenha(SENHA_INVALIDO4_SEM_SIMBOLOS);
		Set<ConstraintViolation<CadastroBean>> res = validator.validate(cad);
		//System.out.println(res);
		Assertions.assertEquals(1, res.size());

		temMensagem(res, CAMPO_SENHA, MSG_SENHA);
	}

	@Test
	public void senhaNaoContemSimbolosNemLetras() {
		CadastroBean cad = beanValido();
		cad.setSenha(SENHA_INVALIDO5_SEM_SIMBOLOS_LETRAS);
		cad.setConfirmacaoSenha(SENHA_INVALIDO5_SEM_SIMBOLOS_LETRAS);
		Set<ConstraintViolation<CadastroBean>> res = validator.validate(cad);
		//System.out.println(res);
		Assertions.assertEquals(1, res.size());

		temMensagem(res, CAMPO_SENHA, MSG_SENHA);
	}

	@Test
	public void senhaNaoContemSimbolosNemNumeros() {
		CadastroBean cad = beanValido();
		cad.setSenha(SENHA_INVALIDO6_SEM_SIMBOLOS_NUMEROS);
		cad.setConfirmacaoSenha(SENHA_INVALIDO6_SEM_SIMBOLOS_NUMEROS);
		Set<ConstraintViolation<CadastroBean>> res = validator.validate(cad);
		//System.out.println(res);
		Assertions.assertEquals(1, res.size());
		
		temMensagem(res, CAMPO_SENHA, MSG_SENHA);
	}

	@Test
	public void senhaNaoConfere() {
		CadastroBean cad = beanValido();
		cad.setSenha(SENHA_VALIDO1);
		cad.setConfirmacaoSenha(SENHA_VALIDO2);
		Set<ConstraintViolation<CadastroBean>> res = validator.validate(cad);
		//System.out.println(res);
		Assertions.assertEquals(1, res.size());
		
		temMensagem(res, CAMPO_SENHA, "Os campos devem ser iguais");
	}

	@Test
	public void emailInvalido() {
		CadastroBean cad = beanValido();
		cad.setEmail(EMAIL_INVALIDO1);
		Set<ConstraintViolation<CadastroBean>> res = validator.validate(cad);
		//System.out.println(res);
		Assertions.assertEquals(1, res.size());
		
		temMensagem(res, CAMPO_EMAIL, "não é um endereço de e-mail");
	}

	@Test
	public void emailVazio() {
		CadastroBean cad = beanValido();
		cad.setEmail("");
		Set<ConstraintViolation<CadastroBean>> res = validator.validate(cad);
		//System.out.println(res);
		Assertions.assertEquals(1, res.size());
		
		temMensagem(res, CAMPO_EMAIL, "não pode estar vazio");
	}

	@Test
	public void emailNulo() {
		CadastroBean cad = beanValido();
		cad.setEmail(null);
		Set<ConstraintViolation<CadastroBean>> res = validator.validate(cad);
		//System.out.println(res);
		Assertions.assertEquals(1, res.size());

		temMensagem(res, CAMPO_EMAIL, "não pode estar vazio");
	}

	@Test
	public void nomeVazio() {
		CadastroBean cad = beanValido();
		cad.setNome("");
		Set<ConstraintViolation<CadastroBean>> res = validator.validate(cad);
		//System.out.println(res);
		Assertions.assertEquals(1, res.size());
		
		temMensagem(res, CAMPO_NOME, "não pode estar vazio");
	}

	@Test
	public void nomeNulo() {
		CadastroBean cad = beanValido();
		cad.setNome(null);
		Set<ConstraintViolation<CadastroBean>> res = validator.validate(cad);
		//System.out.println(res);
		Assertions.assertEquals(1, res.size());
		
		temMensagem(res, CAMPO_NOME, "não pode estar vazio");
	}

	@Test
	public void cpfInvalido() {
		CadastroBean cad = beanValido();
		cad.setCpf(CPF_INVALIDO1);
		Set<ConstraintViolation<CadastroBean>> res = validator.validate(cad);
		//System.out.println(res);
		Assertions.assertEquals(1, res.size());
		
		temMensagem(res, CAMPO_CPF, "CPF inválido");
	}

	@Test
	public void cpfVazio() {
		CadastroBean cad = beanValido();
		cad.setCpf("");
		Set<ConstraintViolation<CadastroBean>> res = validator.validate(cad);
		//System.out.println(res);
		Assertions.assertEquals(2, res.size());
		
		temMensagem(res, CAMPO_CPF, "CPF inválido");
		temMensagem(res, CAMPO_CPF, "não pode estar vazio");
	}

	@Test
	public void cpfNulo() {
		CadastroBean cad = beanValido();
		cad.setCpf(null);
		Set<ConstraintViolation<CadastroBean>> res = validator.validate(cad);
		System.out.println(res);
		Assertions.assertEquals(2, res.size());
		
		temMensagem(res, CAMPO_CPF, "não pode ser nulo");
		temMensagem(res, CAMPO_CPF, "não pode estar vazio");
	}

}
