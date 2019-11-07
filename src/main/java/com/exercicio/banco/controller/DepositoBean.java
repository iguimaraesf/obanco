package com.exercicio.banco.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;

public class DepositoBean {
	@NotNull
	private Long contaDestino;
	@DecimalMin(value = "0.01")
	@NotNull
	private BigDecimal valor;
	@CreditCardNumber(message = "Número inválido de cartão de crédito")
	@NotNull
	private String cartaoCredito;
	@Future
	@NotNull
	private LocalDate dataValidade;
	@Min(0)
	@Max(9999)
	@NotNull
	private Integer cvv;

	public Long getContaDestino() {
		return contaDestino;
	}
	public void setContaDestino(Long contaDestino) {
		this.contaDestino = contaDestino;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getCartaoCredito() {
		return cartaoCredito;
	}
	public void setCartaoCredito(String cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}
	public LocalDate getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}
	public Integer getCvv() {
		return cvv;
	}
	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

}
