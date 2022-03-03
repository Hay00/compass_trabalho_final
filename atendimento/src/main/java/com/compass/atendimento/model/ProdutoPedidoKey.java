package com.compass.atendimento.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class ProdutoPedidoKey implements Serializable {

	@Column(name = "pedido_id")
	private Long pedidoId;

	@Column(name = "produto_id")
	private Long produtoId;
}
