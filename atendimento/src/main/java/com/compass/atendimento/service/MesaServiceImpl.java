package com.compass.atendimento.service;

import java.util.List;

import com.compass.atendimento.model.Mesa;
import com.compass.atendimento.repository.MesaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MesaServiceImpl implements MesaService {

	@Autowired
	private MesaRepository mesaRepository;

	@Override
	public List<Mesa> findAll() {
		return mesaRepository.findAll();
	}

	@Override
	public Mesa findById(String id) {
		return mesaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Mesa não encontrada"));
	}

	@Override
	public Mesa save(Mesa mesa) {
		return mesaRepository.save(mesa);
	}

	@Override
	public Mesa update(String id, Mesa newMesa) {
		return mesaRepository.findById(id).map(mesa -> {
			mesa.setNumero(newMesa.getNumero());
			mesa.setCapacidade(newMesa.getCapacidade());
			mesa.setOcupada(newMesa.isOcupada());
			return mesaRepository.save(mesa);
		}).orElseThrow(() -> new IllegalArgumentException("Mesa não encontrada"));
	}

	@Override
	public void delete(String id) {
		mesaRepository.deleteById(id);
	}
}
