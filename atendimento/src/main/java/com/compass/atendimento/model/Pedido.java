package com.compass.atendimento.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.compass.atendimento.dto.ProdutoDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String statusPedido;

	private BigDecimal valorTotal;

	@ManyToOne
	private Conta conta;

	// TODO: Lista de produtos
	@OneToMany
	private List<ProdutoPedido> produtos;

	public Pedido(String statusPedido, BigDecimal valorTotal) {
		this.statusPedido = statusPedido;
		this.valorTotal = valorTotal;
	}

}
