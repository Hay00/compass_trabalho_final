package com.compass.atendimento.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.compass.atendimento.dto.ContaDto;
import com.compass.atendimento.form.ContaForm;
import com.compass.atendimento.model.Conta;
import com.compass.atendimento.repository.ContaRepository;

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

	@GetMapping
	public List<ContaDto> all() {
		return ContaDto.converter(contaRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContaDto> one(@PathVariable Long id) {
		return contaRepository.findById(id)
				.map(conta -> ResponseEntity.ok(new ContaDto(conta)))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<ContaDto> newConta(@RequestBody @Valid ContaForm form, UriComponentsBuilder uriBuilder) {
		Conta conta = form.converter();
		contaRepository.save(conta);
		URI uri = uriBuilder.path("/conta").buildAndExpand(conta.getId()).toUri();
		return ResponseEntity.created(uri).body(new ContaDto(conta));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ContaDto> updateConta(@PathVariable Long id, @RequestBody @Valid ContaForm form) {
		Conta contaForm = form.converter();
		return contaRepository.findById(id)
				.map(conta -> {
					conta.setValorTotal(contaForm.getValorTotal());
					conta.setStatus(contaForm.isStatus());
					contaRepository.save(conta);
					return ResponseEntity.ok(new ContaDto(conta));
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteConta(@PathVariable Long id) {
		return contaRepository.findById(id)
				.map(conta -> {
					contaRepository.delete(conta);
					return ResponseEntity.ok().build();
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
}
