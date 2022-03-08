package com.compass.atendimento.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.compass.atendimento.model.ProdutoPedido;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProdutoDto {

	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private String tipo;
	private boolean status;

	public ProdutoDto(ProdutoPedido produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
	}

	public static List<ProdutoDto> converter(List<ProdutoPedido> produtos) {
		return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
	}
}
