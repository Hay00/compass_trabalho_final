package com.compass.atendimento.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.compass.atendimento.model.Pedido;
import com.compass.atendimento.model.ProdutoPedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDto {

	private String id;
	private String conta;
	private String statusPedido;
	private BigDecimal valorTotal;
	private List<ProdutoPedido> produtos;

	public PedidoDto(Pedido pedido) {
		this.id = pedido.getId();
		this.statusPedido = pedido.getStatusPedido();
		this.valorTotal = pedido.getValorTotal();
		this.conta = pedido.getConta().getId();
		this.produtos = pedido.getProdutos();
	}

	public static List<PedidoDto> converter(List<Pedido> pedidos) {
		return pedidos.stream().map(PedidoDto::new).collect(Collectors.toList());
	}
}
