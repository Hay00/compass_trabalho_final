package com.compass.atendimento.form;

import java.math.BigDecimal;

import com.compass.atendimento.model.Pedido;

import lombok.Setter;

@Setter
public class PedidoForm {
    
	private Long id;
    private String statusPedido;
    private BigDecimal valorTotal;
    
    public Pedido converter() {
		return null;
    }
    public Pedido atualizar() {
    	return null;
    }
}
