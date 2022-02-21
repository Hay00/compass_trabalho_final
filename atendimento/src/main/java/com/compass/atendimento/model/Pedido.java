package com.compass.atendimento.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private String statusPedido;
    private BigDecimal valorTotal;
    //private List<ItemPedido>;
    
    
	public Pedido(String statusPedido, BigDecimal valorTotal) {
		super();
		this.statusPedido = statusPedido;
		this.valorTotal = valorTotal;
	}
    
    
}
