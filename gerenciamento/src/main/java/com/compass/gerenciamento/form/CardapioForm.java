package com.compass.gerenciamento.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.compass.gerenciamento.model.Cardapio;

import lombok.Setter;

@Setter
public class CardapioForm {
    
	@NotNull @NotEmpty
    private String nome;
    //List <ItemPedido> produtos;
	
	public Cardapio converter() {
		return null;
    }
    
    public Cardapio atualizar() {
    	return null;
    }
}
