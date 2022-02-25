package com.compass.atendimento.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.compass.atendimento.config.validation.form.StatusPedidoAnnotation;
import com.compass.atendimento.model.Pedido;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoForm {

	@NotNull
	private Long contaId;

	@NotNull
	@StatusPedidoAnnotation
	private String statusPedido;

	@NotNull
	private BigDecimal valorTotal;

	public Pedido converter() {
		return new Pedido(statusPedido, valorTotal);
	}
}
