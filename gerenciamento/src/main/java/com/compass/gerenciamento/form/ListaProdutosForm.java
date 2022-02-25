package com.compass.gerenciamento.form;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ListaProdutosForm {
	
	@NotNull
	private List<Long> produtos;
	
}
