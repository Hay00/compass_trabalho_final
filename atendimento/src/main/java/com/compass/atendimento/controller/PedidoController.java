package com.compass.atendimento.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.compass.atendimento.dto.PedidoDto;
import com.compass.atendimento.form.PedidoForm;
import com.compass.atendimento.model.Pedido;
import com.compass.atendimento.repository.PedidoRepository;

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
	private PedidoRepository pedidoRepository;

	@GetMapping
	public List<PedidoDto> all() {
		return PedidoDto.converter(pedidoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PedidoDto> one(@PathVariable Long id) {
		return pedidoRepository.findById(id)
				.map(pedido -> ResponseEntity.ok(new PedidoDto(pedido)))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<PedidoDto> newPedido(@RequestBody @Valid PedidoForm form, UriComponentsBuilder uriBuilder) {
		Pedido pedido = form.converter();
		pedidoRepository.save(pedido);
		URI uri = uriBuilder.path("/pedidos").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).body(new PedidoDto(pedido));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PedidoDto> updatePedido(@PathVariable Long id, @RequestBody @Valid PedidoForm form) {
		Pedido pedidoForm = form.converter();
		return pedidoRepository.findById(id)
				.map(pedido -> {
					pedido.setValorTotal(pedidoForm.getValorTotal());
					pedido.setStatusPedido(pedidoForm.getStatusPedido());
					pedidoRepository.save(pedido);
					return ResponseEntity.ok(new PedidoDto(pedido));
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletePedido(@PathVariable Long id) {
		return pedidoRepository.findById(id)
				.map(pedido -> {
					pedidoRepository.delete(pedido);
					return ResponseEntity.noContent().build();
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
}
