package com.compass.atendimento.form;

import java.math.BigDecimal;

import com.compass.atendimento.model.Pedido;

import lombok.Setter;

@Setter
public class PedidoForm {

	private Long id;
	private String statusPedido;
	private BigDecimal valorTotal;

	public Pedido converter() {
		// TODO: Verificar a conta
		return new Pedido(statusPedido, valorTotal, null);
	}
}
