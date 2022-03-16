package com.compass.gerenciamento.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	private String nome;

	@NotNull
	@NotEmpty
	private String descricao;

	@NotNull
	private BigDecimal preco;

	@NotNull
	@NotEmpty
	private String tipo;

	@NotNull
	private boolean status;

	public Produto(String nome, String descricao, String tipo, BigDecimal preco, boolean status) {
		this.nome = nome;
		this.descricao = descricao;
		this.tipo = tipo;
		this.preco = preco;
		this.status = status;
	}

}
