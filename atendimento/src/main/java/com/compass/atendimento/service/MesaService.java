package com.compass.atendimento.service;

import java.util.List;

import com.compass.atendimento.model.Mesa;

public interface MesaService {

	List<Mesa> findAll();

	Mesa findById(String id);

	Mesa save(Mesa mesa);

	Mesa update(String id, Mesa newMesa);

	void delete(String id);
}
