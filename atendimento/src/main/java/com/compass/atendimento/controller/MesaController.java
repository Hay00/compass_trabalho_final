package com.compass.atendimento.controller;

import java.util.List;

import javax.validation.Valid;

import com.compass.atendimento.dto.MesaDto;
import com.compass.atendimento.form.MesaForm;
import com.compass.atendimento.model.Mesa;
import com.compass.atendimento.service.MesaService;

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
	private MesaService mesaService;

	@GetMapping
	public List<MesaDto> all() {
		return MesaDto.converter(mesaService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<MesaDto> one(@PathVariable String id) {
		return ResponseEntity.ok(new MesaDto(mesaService.findById(id)));
	}

	@PostMapping
	public ResponseEntity<MesaDto> newMesa(@RequestBody @Valid MesaForm form, UriComponentsBuilder uriBuilder) {
		Mesa mesa = form.converter();
		return ResponseEntity.created(uriBuilder.path("/mesas").buildAndExpand(mesa.getId()).toUri())
				.body(new MesaDto(mesaService.save(mesa)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<MesaDto> updateMesa(@PathVariable String id, @RequestBody @Valid MesaForm form) {
		return ResponseEntity.ok(new MesaDto(mesaService.update(id, form.converter())));
	}

	@DeleteMapping("/{id}")
	public void deleteMesa(@PathVariable String id) {
		mesaService.delete(id);
	}
}
