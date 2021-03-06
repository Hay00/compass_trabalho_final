package com.compass.atendimento.controller;

import java.util.List;

import javax.validation.Valid;

import com.compass.atendimento.dto.ContaDto;
import com.compass.atendimento.form.ContaForm;
import com.compass.atendimento.model.Conta;
import com.compass.atendimento.service.ContaService;

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
	private ContaService contaService;

	@GetMapping
	public List<ContaDto> all() {
		return ContaDto.converter(contaService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContaDto> one(@PathVariable String id) {
		return ResponseEntity.ok(new ContaDto(contaService.findById(id)));
	}

	@PostMapping
	public ResponseEntity<ContaDto> newConta(@RequestBody @Valid ContaForm form, UriComponentsBuilder uriBuilder) {
		Conta conta = form.converter();
		return ResponseEntity.created(uriBuilder.path("/conta").buildAndExpand(conta.getId()).toUri())
				.body(new ContaDto(contaService.save(conta, form.getMesaId())));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ContaDto> updateConta(@PathVariable String id, @RequestBody @Valid ContaForm form) {
		return ResponseEntity.ok(new ContaDto(contaService.update(id, form.converter(), form.getMesaId())));
	}

	@DeleteMapping("/{id}")
	public void deleteConta(@PathVariable String id) {
		contaService.delete(id);
	}
}
