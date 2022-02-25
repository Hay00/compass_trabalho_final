package com.compass.atendimento.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.compass.atendimento.dto.MesaDto;
import com.compass.atendimento.form.MesaForm;
import com.compass.atendimento.model.Mesa;
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
@RequestMapping("/mesas")
public class MesaController {

	@Autowired
	private MesaRepository mesaRepository;

	@GetMapping
	public List<MesaDto> all() {
		// TODO: Implementar filtro com somente contas ativas
		return MesaDto.converter(mesaRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<MesaDto> one(@PathVariable Long id) {
		// TODO: Implementar filtro com somente contas ativas
		return mesaRepository.findById(id).map(mesa -> ResponseEntity.ok(new MesaDto(mesa)))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<MesaDto> newMesa(@RequestBody @Valid MesaForm form, UriComponentsBuilder uriBuilder) {
		Mesa mesa = form.converter();
		mesaRepository.save(mesa);
		URI uri = uriBuilder.path("/mesa").buildAndExpand(mesa.getId()).toUri();
		return ResponseEntity.created(uri).body(new MesaDto(mesa));
	}

	@PutMapping("/{id}")
	public ResponseEntity<MesaDto> updateMesa(@PathVariable Long id, @RequestBody @Valid MesaForm form) {
		Mesa mesaForm = form.converter();
		return mesaRepository.findById(id).map(mesa -> {
			mesa.setNumero(mesaForm.getNumero());
			mesa.setCapacidade(mesaForm.getCapacidade());
			mesa.setOcupada(mesaForm.isOcupada());
			mesaRepository.save(mesa);
			return ResponseEntity.ok(new MesaDto(mesa));
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteMesa(@PathVariable Long id) {
		return mesaRepository.findById(id).map(mesa -> {
			mesaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}
}
