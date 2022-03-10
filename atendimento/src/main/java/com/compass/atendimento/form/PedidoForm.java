package com.compass.atendimento.form;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.compass.atendimento.config.validation.form.StatusPedidoAnnotation;
import com.compass.atendimento.model.Pedido;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoForm {

	@NotNull
	private String contaId;

	@NotNull
	@StatusPedidoAnnotation
	private String statusPedido;

	@NotNull
	private BigDecimal valorTotal;

	// TODO: Adicionar quantidades de produtos
	@NotNull
	private List<Long> produtosIds;

	public Pedido converter() {
		return new Pedido(statusPedido, valorTotal);
	}
}
