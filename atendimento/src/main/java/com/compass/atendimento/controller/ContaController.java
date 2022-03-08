package com.compass.atendimento.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.compass.atendimento.dto.ContaDto;
import com.compass.atendimento.form.ContaForm;
import com.compass.atendimento.model.Conta;
import com.compass.atendimento.repository.ContaRepository;
import com.compass.atendimento.repository.MesaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	MesaRepository mesaRepository;

	@GetMapping
	public List<ContaDto> all() {
		return ContaDto.converter(contaRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContaDto> one(@PathVariable String id) {
		return contaRepository.findById(id).map(conta -> ResponseEntity.ok(new ContaDto(conta)))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<ContaDto> newConta(@RequestBody @Valid ContaForm form, UriComponentsBuilder uriBuilder) {
		Conta conta = form.converter();
		return mesaRepository.findById(form.getMesaId()).map(mesa -> {
			conta.setMesa(mesa);
			contaRepository.save(conta);
			URI uri = uriBuilder.path("/conta").buildAndExpand(conta.getId()).toUri();
			return ResponseEntity.created(uri).body(new ContaDto(conta));
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<ContaDto> updateConta(@PathVariable String id, @RequestBody @Valid ContaForm form) {
		Optional<Conta> optional = contaRepository.findById(id);
		if (optional.isPresent()) {
			return mesaRepository.findById(form.getMesaId()).map(mesa -> {
				Conta conta = optional.get();
				conta.setMesa(mesa);
				conta.setValorTotal(form.getValorTotal());
				conta.setStatus(form.getStatus());
				contaRepository.save(conta);
				return ResponseEntity.ok(new ContaDto(conta));
			}).orElseGet(() -> ResponseEntity.notFound().build());

		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteConta(@PathVariable String id) {
		return contaRepository.findById(id).map(conta -> {
			contaRepository.delete(conta);
			return ResponseEntity.ok().build();
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}
}
