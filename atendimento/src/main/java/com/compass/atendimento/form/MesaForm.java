package com.compass.atendimento.form;

import javax.validation.constraints.NotNull;

import com.compass.atendimento.model.Mesa;

import lombok.Setter;

@Setter
public class MesaForm {

	@NotNull
	private String numero;

	@NotNull
	private Integer capacidade;

	@NotNull
	private Boolean ocupada;

	public Mesa converter() {
		return new Mesa(numero, capacidade, ocupada);
	}
}
