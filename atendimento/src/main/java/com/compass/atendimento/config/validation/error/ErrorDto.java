package com.compass.atendimento.config.validation.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorDto {

	private String campo;
	private String erro;
}
