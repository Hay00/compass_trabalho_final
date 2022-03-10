package com.compass.atendimento.service;

import java.util.List;

import com.compass.atendimento.model.Conta;

public interface ContaService {

	Conta findById(String id);

	List<Conta> findAll();

	Conta save(Conta conta, String mesaId);

	Conta update(String id, Conta newConta, String mesaId);

	void delete(String id);
}
