package com.exercicio.banco.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

public class ValidadorCamposIguais implements ConstraintValidator<CamposIguais, Object> {
	private String nomeCampo1;
	private String nomeCampo2;
	private String mensagem;

	@Override
	public void initialize(CamposIguais constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
		this.nomeCampo1 = constraintAnnotation.primeiro();
		this.nomeCampo2 = constraintAnnotation.segundo();
		this.mensagem = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Object o1, o2;
		try {
			o1 = BeanUtils.getProperty(value, nomeCampo1);
		} catch (Exception e) {
			context.buildConstraintViolationWithTemplate("Campo " + nomeCampo1 + " não encontrado na classe " + value.getClass().getName())
				.addPropertyNode(nomeCampo1)
				.addConstraintViolation()
				.disableDefaultConstraintViolation();
			return false;
		}
		try {
			o2 = BeanUtils.getProperty(value, nomeCampo2);
		} catch (Exception e) {
			context.buildConstraintViolationWithTemplate("Campo " + nomeCampo2 + " não encontrado na classe " + value.getClass().getName())
				.addPropertyNode(nomeCampo2)
				.addConstraintViolation()
				.disableDefaultConstraintViolation();
			return false;
		}

		boolean valido = o1 == null && o2 == null || o1 != null && o1.equals(o2);
		if (!valido) {
			context.buildConstraintViolationWithTemplate(mensagem)
				.addPropertyNode(nomeCampo1)
				.addConstraintViolation()
				.disableDefaultConstraintViolation();
		}
		return valido;
	}

}
