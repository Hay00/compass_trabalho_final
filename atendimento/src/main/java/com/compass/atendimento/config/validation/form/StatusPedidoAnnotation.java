package com.compass.atendimento.config.validation.form;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StatusPedidoValidation.class)
public @interface StatusPedidoAnnotation {
	static List<String> allowedStates = List.of("Pendente", "Cancelado", "Finalizado");

	String message() default "Status do pedido inválido, valores válidos: Pendente, Cancelado, Finalizado";

    Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};
}
