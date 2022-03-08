package com.compass.atendimento.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.compass.atendimento.model.Conta;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContaForm {

	@NotNull
	private String mesaId;

	@NotNull
	private Boolean status;

	@NotNull
	private BigDecimal valorTotal;

	public Conta converter() {
		return new Conta(valorTotal, status);
	}

}
