package com.compass.atendimento.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Conta {

	@Id
	private String id;

	@DBRef
	private Mesa mesa;

	private BigDecimal valorTotal;

	private boolean status;

	@DBRef
	private List<Pedido> pedidos;

	public Conta(BigDecimal valorTotal, boolean status) {
		this.valorTotal = valorTotal;
		this.status = status;
		this.pedidos = new ArrayList<>();
	}

}
