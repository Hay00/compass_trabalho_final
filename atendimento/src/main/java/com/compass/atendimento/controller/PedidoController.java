package com.compass.atendimento.controller;

import java.util.List;

import javax.validation.Valid;

import com.compass.atendimento.dto.PedidoDto;
import com.compass.atendimento.form.PedidoForm;
import com.compass.atendimento.model.Pedido;
import com.compass.atendimento.service.PedidoService;

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
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@GetMapping
	public List<PedidoDto> all() {
		return PedidoDto.converter(pedidoService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PedidoDto> one(@PathVariable String id) {
		return ResponseEntity.ok(new PedidoDto(pedidoService.findById(id)));
	}

	@PostMapping
	public ResponseEntity<PedidoDto> newPedido(@RequestBody @Valid PedidoForm form, UriComponentsBuilder uriBuilder) {
		Pedido pedido = form.converter();
		return ResponseEntity.created(uriBuilder.path("/pedidos").buildAndExpand(pedido.getId()).toUri())
				.body(new PedidoDto(pedidoService.save(pedido, form.getContaId(), form.getProdutos())));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PedidoDto> updatePedido(@PathVariable String id, @RequestBody @Valid PedidoForm form) {
		Pedido pedido = form.converter();
		return ResponseEntity.ok(new PedidoDto(pedidoService.update(id, pedido, form.getProdutos(), form.getContaId())));
	}

	@DeleteMapping("/{id}")
	public void deletePedido(@PathVariable String id) {
		pedidoService.delete(id);
	}
}
