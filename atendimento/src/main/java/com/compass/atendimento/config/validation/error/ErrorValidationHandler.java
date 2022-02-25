package com.compass.atendimento.config.validation.error;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorValidationHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrorDto> handleInvalidArgument(MethodArgumentNotValidException exception) {
		List<ErrorDto> errors = new ArrayList<>();
		exception.getBindingResult().getFieldErrors().forEach(e -> {
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			errors.add(new ErrorDto(e.getField(), message));
		});
		return errors;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PropertyReferenceException.class)
	public ErrorDto handlePropertyReference(PropertyReferenceException exception) {
		return new ErrorDto(exception.getPropertyName(), exception.getMessage());
	}

}
