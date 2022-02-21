package com.compass.atendimento.form;

import javax.validation.constraints.NotNull;

import com.compass.atendimento.model.Mesa;

import lombok.Setter;

@Setter
@NotNull
public class MesaForm {

	@NotNull
	private boolean status;

	public Mesa converter() {
		return new Mesa(status);
	}
}
