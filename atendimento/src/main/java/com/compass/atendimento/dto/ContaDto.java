package com.compass.atendimento.dto;

import java.math.BigDecimal;
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
public class ContaDto {
    private Long id;
    private Mesa idMesa;
    private BigDecimal valorTotal;
    private boolean status;
    
	public ContaDto(Conta conta) {
		this.id = conta.getId();
		this.idMesa = conta.getIdMesa();
		this.valorTotal = conta.getValorTotal();
		this.status = conta.isStatus();
	}
    
	public static List<ContaDto> converter(List<Conta> contas) {
		return contas.stream().map(ContaDto::new).collect(Collectors.toList());
	}
}
