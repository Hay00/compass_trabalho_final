package com.compass.atendimento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProdutoDto {
	private Long id;
	private String nome;
	private String descricao;
	private String tipo;
	private boolean status;
}
