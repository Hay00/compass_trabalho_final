package com.compass.atendimento.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Mesa mesa;

	@NotNull
	private BigDecimal valorTotal;

	@NotNull
	private boolean status;

	@OneToMany(mappedBy = "conta")
	private List<Pedido> pedidos;

	public Conta(BigDecimal valorTotal, boolean status) {
		this.valorTotal = valorTotal;
		this.status = status;
		this.pedidos = new ArrayList<>();
	}

}
