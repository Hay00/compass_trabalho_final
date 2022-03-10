package com.compass.atendimento.form;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProdutosForm {

	@NotNull
	private Long id;

	@NotNull
	private Long quantidade;
}
