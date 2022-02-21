package com.compass.atendimento.model;

import java.math.BigDecimal;
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

	@OneToMany
	private List<Pedido> pedidos;

	public Conta(Long idMesa, BigDecimal valorTotal, boolean status) {
		this.mesa.setId(idMesa);
		this.valorTotal = valorTotal;
		this.status = status;
	}

}
