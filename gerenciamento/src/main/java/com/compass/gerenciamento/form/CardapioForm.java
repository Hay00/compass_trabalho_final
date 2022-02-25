package com.compass.gerenciamento.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.compass.gerenciamento.model.Cardapio;
import com.compass.gerenciamento.model.Produto;

import lombok.Setter;

@Setter
public class CardapioForm {

	@NotNull
	@NotEmpty
	private String nome;
	// private List<Produto> produto;

	public Cardapio convert() {
		return new Cardapio(nome, new ArrayList<>());
	}

}
