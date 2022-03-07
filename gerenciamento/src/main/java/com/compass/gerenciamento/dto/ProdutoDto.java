package com.compass.gerenciamento.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.compass.gerenciamento.model.Produto;

import lombok.Getter;

@Getter
public class ProdutoDto {

	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private String tipo;
	private boolean status;

	public ProdutoDto(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.preco = produto.getPreco();
		this.tipo = produto.getTipo();
		this.status = produto.isStatus();
	}

	public static List<ProdutoDto> convert(List<Produto> produtos) {
		return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
	}
}
