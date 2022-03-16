package com.compass.gerenciamento.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.compass.gerenciamento.model.Produto;

import lombok.Setter;

@Setter
public class ProdutoForm {

	@NotNull
	@NotEmpty
	private String nome;

	@NotNull
	@NotEmpty
	private String descricao;

	@NotNull
	@NotEmpty
	private String tipo;

	@NotNull
	private BigDecimal preco;

	@NotNull
	private boolean status;

	public Produto convert() {
		return new Produto(nome, descricao, tipo, preco, status);
	}
}
