package com.compass.atendimento.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.compass.atendimento.model.Conta;

import lombok.Setter;

@Setter
public class ContaForm {
    
	@NotNull
	private BigDecimal valorTotal;
    @NotNull
    private boolean status;
    
    public Conta converter() {
		return null;
    }
    
}
