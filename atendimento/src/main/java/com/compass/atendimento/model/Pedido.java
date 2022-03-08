package com.compass.atendimento.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "pedido")
public class Pedido {

	@Id
	private String id;

	private String statusPedido;

	private BigDecimal valorTotal;

	private Conta conta;

	private List<ProdutoPedido> produtos;

	public Pedido(String statusPedido, BigDecimal valorTotal) {
		this.statusPedido = statusPedido;
		this.valorTotal = valorTotal;
	}
}
