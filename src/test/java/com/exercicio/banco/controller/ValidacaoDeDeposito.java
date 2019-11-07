package com.exercicio.banco.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ValidacaoDeDeposito {

	private static final String CAMPO_CONTA = "contaDestino";
	private static final String CAMPO_VALOR = "valor";
	private static final String CAMPO_CARTAO = "cartaoCredito";
	private static final String CAMPO_VALIDADE = "dataValidade";
	private static final String CAMPO_CVV = "cvv";
	private static final String MSG_NAO_NULO = "não pode ser nulo";
	private static final String MSG_VALOR_MAIOR = "deve ser maior ou igual a 0.01";
	private static final String MSG_CARTAO_CREDITO = "Número inválido de cartão de crédito";
	private static final String MSG_DATA_VALIDADE = "deve estar no futuro";
	private static final String MSG_CVV1 = "deve ser menor ou igual a 9999";
	private static final String MSG_CVV2 = "deve ser maior ou igual a 0";
	private static Validator validator;

	private DepositoBean beanValido() {
		DepositoBean res = new DepositoBean();
		res.setCartaoCredito("5374610079830462");
		res.setContaDestino(1L);
		res.setCvv(123);
		res.setDataValidade(LocalDate.now().plusMonths(4));
		res.setValor(BigDecimal.TEN);
		return res;
	}
	
	private void temMensagem(Set<ConstraintViolation<DepositoBean>> res, String campo, String mensagem) {
		Iterator<ConstraintViolation<DepositoBean>> it = res.iterator();
		String campoComp = "";
		String mensComp = "";
		while (it.hasNext()) {
			ConstraintViolation<DepositoBean> c1 = it.next();
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
	public void semContaDestino() {
		DepositoBean cad = beanValido();
		cad.setContaDestino(null);

		Set<ConstraintViolation<DepositoBean>> res = validator.validate(cad);
		Assertions.assertEquals(1, res.size());

		temMensagem(res, CAMPO_CONTA, MSG_NAO_NULO);
	}

	@Test
	public void semValor() {
		DepositoBean cad = beanValido();
		cad.setValor(null);

		Set<ConstraintViolation<DepositoBean>> res = validator.validate(cad);
		//System.out.println(res);
		Assertions.assertEquals(1, res.size());

		temMensagem(res, CAMPO_VALOR, MSG_NAO_NULO);
	}

	@Test
	public void valorNegativo() {
		DepositoBean cad = beanValido();
		cad.setValor(new BigDecimal("-5.21"));

		Set<ConstraintViolation<DepositoBean>> res = validator.validate(cad);
		//System.out.println(res);
		Assertions.assertEquals(1, res.size());

		temMensagem(res, CAMPO_VALOR, MSG_VALOR_MAIOR);
	}

	@Test
	public void cartaoCreditoInvalido() {
		DepositoBean cad = beanValido();
		cad.setCartaoCredito("123aaa");

		Set<ConstraintViolation<DepositoBean>> res = validator.validate(cad);
		//System.out.println(res);
		Assertions.assertEquals(1, res.size());

		temMensagem(res, CAMPO_CARTAO, MSG_CARTAO_CREDITO);
	}

	@Test
	public void semCartaoCredito() {
		DepositoBean cad = beanValido();
		cad.setCartaoCredito(null);

		Set<ConstraintViolation<DepositoBean>> res = validator.validate(cad);
		Assertions.assertEquals(1, res.size());

		temMensagem(res, CAMPO_CARTAO, MSG_NAO_NULO);
	}

	@Test
	public void semDataValidade() {
		DepositoBean cad = beanValido();
		cad.setDataValidade(null);

		Set<ConstraintViolation<DepositoBean>> res = validator.validate(cad);
		Assertions.assertEquals(1, res.size());

		temMensagem(res, CAMPO_VALIDADE, MSG_NAO_NULO);
	}

	@Test
	public void dataValidadePassou() {
		DepositoBean cad = beanValido();
		cad.setDataValidade(LocalDate.now().minusDays(1));

		Set<ConstraintViolation<DepositoBean>> res = validator.validate(cad);
		Assertions.assertEquals(1, res.size());

		temMensagem(res, CAMPO_VALIDADE, MSG_DATA_VALIDADE);
	}

	@Test
	public void semCVV() {
		DepositoBean cad = beanValido();
		cad.setCvv(null);

		Set<ConstraintViolation<DepositoBean>> res = validator.validate(cad);
		Assertions.assertEquals(1, res.size());

		temMensagem(res, CAMPO_CVV, MSG_NAO_NULO);
	}

	@Test
	public void cvvMaior9999() {
		DepositoBean cad = beanValido();
		cad.setCvv(10000);

		Set<ConstraintViolation<DepositoBean>> res = validator.validate(cad);
		Assertions.assertEquals(1, res.size());

		temMensagem(res, CAMPO_CVV, MSG_CVV1);
	}

	@Test
	public void cvvMenorZero() {
		DepositoBean cad = beanValido();
		cad.setCvv(-1);

		Set<ConstraintViolation<DepositoBean>> res = validator.validate(cad);
		Assertions.assertEquals(1, res.size());

		temMensagem(res, CAMPO_CVV, MSG_CVV2);
	}

	@Test
	public void dadosCorretos() {
		DepositoBean cad = beanValido();

		Set<ConstraintViolation<DepositoBean>> res = validator.validate(cad);
		Assertions.assertEquals(0, res.size());
	}

}

