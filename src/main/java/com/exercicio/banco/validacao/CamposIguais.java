package com.exercicio.banco.validacao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidadorCamposIguais.class)
@Documented
public @interface CamposIguais {
	String message() default "Os campos devem ser iguais";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String primeiro();
    String segundo();

	/*@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface Lista
    {
        CamposIguais[] value();
    }*/
}
