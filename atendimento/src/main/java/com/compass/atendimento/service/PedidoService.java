package com.compass.atendimento.service;

import java.util.List;

import com.compass.atendimento.form.ProdutosForm;
import com.compass.atendimento.model.Pedido;

public interface PedidoService {

	List<Pedido> findAll();

	Pedido findById(String id);

	Pedido save(Pedido pedido, String contaId, List<ProdutosForm> formList);

	Pedido update(String id, Pedido newPedido, List<ProdutosForm> formList, String contaId);

	void delete(String id);
}
