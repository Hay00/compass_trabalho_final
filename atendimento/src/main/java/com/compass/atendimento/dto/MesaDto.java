package com.compass.atendimento.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.compass.atendimento.model.Conta;
import com.compass.atendimento.model.Mesa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MesaDto {

	private Long id;
	private boolean status;
	private List<Long> contas;

	public MesaDto(Mesa mesa) {
		this.id = mesa.getId();
		this.status = mesa.isOcupada();
		if (contas != null) {
			this.contas = mesa.getContas().stream().map(Conta::getId).collect(Collectors.toList());
			// this.contas =
			// mesa.getContas().stream().map(Conta::getId).collect(Collectors.toList());
		} else {
			this.contas = new ArrayList<>();
		}
		// this.conta = ContaDto.converter(mesa.getConta());
	}

	public static List<MesaDto> converter(List<Mesa> mesas) {
		return mesas.stream().map(MesaDto::new).collect(Collectors.toList());
	}
}
