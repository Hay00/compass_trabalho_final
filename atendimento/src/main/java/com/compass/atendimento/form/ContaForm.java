package com.compass.atendimento.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.compass.atendimento.model.Conta;

import lombok.Setter;

@Setter
public class ContaForm {

	@NotNull
	private Long idMesa;

	@NotNull
	private boolean status;

	@NotNull
	private BigDecimal valorTotal;

	public Conta converter() {
		return new Conta(idMesa, valorTotal, status);
	}

}
