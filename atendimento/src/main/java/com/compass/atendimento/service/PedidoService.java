package com.compass.atendimento.service;

import java.util.List;

import com.compass.atendimento.model.Pedido;

public interface PedidoService {

	List<Pedido> findAll();

	Pedido findById(String id);

	Pedido save(Pedido pedido, String contaId, Long[] produtosIds);

	Pedido update(String id, Pedido newPedido, Long[] produtosIds, String contaId);

	void delete(String id);
}
