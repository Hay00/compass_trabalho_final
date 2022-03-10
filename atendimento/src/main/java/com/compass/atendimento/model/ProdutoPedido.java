package com.compass.atendimento.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.compass.atendimento.dto.ProdutoDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoPedido {

	private Long id;
	private String nome;
	private BigDecimal preco;
	private Long quantidade;

	public ProdutoPedido(ProdutoDto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
		this.quantidade = 1L;
	}

	public static List<ProdutoPedido> converter(List<ProdutoDto> pedidosProdutos) {
		return pedidosProdutos.stream().map(ProdutoPedido::new).collect(Collectors.toList());
	}
}
