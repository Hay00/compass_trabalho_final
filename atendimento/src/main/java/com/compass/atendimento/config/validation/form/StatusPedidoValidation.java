package com.compass.atendimento.config.validation.form;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StatusPedidoValidation implements ConstraintValidator<StatusPedidoAnnotation, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) return false;
		return StatusPedidoAnnotation.allowedStates.contains(value);
	}

    @Override
    public void initialize(StatusPedidoAnnotation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
