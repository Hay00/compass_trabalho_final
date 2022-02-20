package com.compass.gerenciamento.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.compass.gerenciamento.model.Cardapio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardapioDto {
	
    private Long id;
    private String nome;
    //List<ItemProduto> produtos;
    
    public CardapioDto(Cardapio cardapio) {
    	this.id = cardapio.getId();
    	this.nome = cardapio.getNome();
    }
    
    public static List<CardapioDto> converter(List<Cardapio> cardapios) {
		return cardapios.stream().map(CardapioDto::new).collect(Collectors.toList());
	}
}
