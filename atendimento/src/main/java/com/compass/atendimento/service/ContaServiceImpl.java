package com.compass.atendimento.service;

import java.util.List;

import com.compass.atendimento.model.Conta;
import com.compass.atendimento.repository.ContaRepository;
import com.compass.atendimento.repository.MesaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaServiceImpl implements ContaService {

	private static final String MESA_NOT_FOUND = "Mesa não encontrada";

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private MesaRepository mesaRepository;

	@Override
	public List<Conta> findAll() {
		return contaRepository.findAll();
	}

	@Override
	public Conta findById(String id) {
		return contaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));
	}

	@Override
	public Conta save(Conta conta, String mesaId) {
		return mesaRepository.findById(mesaId).map(mesa -> {
			conta.setMesa(mesa);
			contaRepository.save(conta);
			mesa.getContas().add(conta);
			mesaRepository.save(mesa);
			return conta;
		}).orElseThrow(() -> new IllegalArgumentException(MESA_NOT_FOUND));
	}

	@Override
	public Conta update(String id, Conta newConta, String mesaId) {
		return contaRepository.findById(id).map(conta -> {
			conta.setValorTotal(newConta.getValorTotal());
			conta.setStatus(newConta.isStatus());

			// Validando se a mesa da conta continua a mesma
			if (conta.getMesa().getId().equals(mesaId)) {
				return mesaRepository.findById(mesaId).map(mesa -> contaRepository.save(conta))
						.orElseThrow(() -> new IllegalArgumentException(MESA_NOT_FOUND));
			}

			// Remove referencia antiga
			mesaRepository.findById(conta.getMesa().getId()).ifPresent(oldMesa -> {
				oldMesa.getContas().removeIf(c -> c.getId().equals(id));
				mesaRepository.save(oldMesa);
			});

			// Adiciona referencia nova
			return mesaRepository.findById(mesaId).map(mesa -> {
				conta.setMesa(mesa);
				contaRepository.save(conta);
				mesa.getContas().add(conta);
				mesaRepository.save(mesa);
				return conta;
			}).orElseThrow(() -> new IllegalArgumentException(MESA_NOT_FOUND));
		}).orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));
	}

	@Override
	public void delete(String id) {
		contaRepository.findById(id)
				.ifPresent(conta -> mesaRepository.findById(conta.getMesa().getId()).ifPresent(mesa -> {
					mesa.getContas().removeIf(c -> c.getId().equals(id));
					mesaRepository.save(mesa);
					contaRepository.delete(conta);
				}));
	}
}
