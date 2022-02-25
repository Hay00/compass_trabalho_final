package com.compass.atendimento.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.compass.atendimento.model.Mesa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MesaDto {

	private Long id;
	private String numero;
	private Integer capacidade;
	private boolean ocupada;
	private List<ContaDto> contas;

	public MesaDto(Mesa mesa) {
		this.id = mesa.getId();
		this.numero = mesa.getNumero();
		this.capacidade = mesa.getCapacidade();
		this.ocupada = mesa.isOcupada();
		this.contas = ContaDto.converter(mesa.getContas());
	}

	public static List<MesaDto> converter(List<Mesa> mesas) {
		return mesas.stream().map(MesaDto::new).collect(Collectors.toList());
	}
}
