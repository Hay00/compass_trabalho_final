package com.compass.gerenciamento.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.compass.gerenciamento.model.Cardapio;
import com.compass.gerenciamento.model.Produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CardapioDto {
	
    private Long id;
    private String nome;
    private List<Produto> produto;
    
    public CardapioDto(Cardapio cardapio) {
    	this.id = cardapio.getId();
    	this.nome = cardapio.getNome();
    	this.produto = cardapio.getProdutos(); 
    }
    
    public static List<CardapioDto> convert(List<Cardapio> cardapios) {
		return cardapios.stream().map(CardapioDto::new).collect(Collectors.toList());
	}
    
}
