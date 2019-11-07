package com.exercicio.banco.controller;

import java.math.BigDecimal;

public class SaldoBean {
	private final Long conta;
	private final BigDecimal saldo;
	public SaldoBean(Long conta, BigDecimal saldo) {
		super();
		this.conta = conta;
		this.saldo = saldo;
	}
	public Long getConta() {
		return conta;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	
}
