package com.compass.atendimento.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.compass.atendimento.model.Conta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaDto {
	private String id;
	private String idMesa;
	private BigDecimal valorTotal;
	private boolean status;
	private List<PedidoDto> pedidos;

	public ContaDto(Conta conta) {
		this.id = conta.getId();
		this.idMesa = conta.getMesa().getId();
		this.valorTotal = conta.getValorTotal();
		this.status = conta.isStatus();
		this.pedidos = PedidoDto.converter(conta.getPedidos());
	}

	public static List<ContaDto> converter(List<Conta> contas) {
		return contas.stream().map(ContaDto::new).collect(Collectors.toList());
	}
}
