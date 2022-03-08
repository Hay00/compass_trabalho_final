package com.compass.atendimento.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.compass.atendimento.dto.PedidoDto;
import com.compass.atendimento.form.PedidoForm;
import com.compass.atendimento.model.Pedido;
import com.compass.atendimento.model.ProdutoPedido;
import com.compass.atendimento.repository.ContaRepository;
import com.compass.atendimento.repository.PedidoRepository;
import com.compass.atendimento.service.ProdutoService;

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

import feign.FeignException;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public List<PedidoDto> all() {
		return PedidoDto.converter(pedidoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PedidoDto> one(@PathVariable String id) {
		return pedidoRepository.findById(id).map(pedido -> ResponseEntity.ok(new PedidoDto(pedido)))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<PedidoDto> newPedido(@RequestBody @Valid PedidoForm form, UriComponentsBuilder uriBuilder) {
		Pedido pedido = form.converter();
		return contaRepository.findById(form.getContaId()).map(conta -> {
			pedido.setConta(conta);
			List<ProdutoPedido> pedidosProdutos = new ArrayList<>();
			for (Long id : form.getProdutosIds()) {
				try {
					pedidosProdutos.add(new ProdutoPedido(produtoService.getProdutoById(id)));
				} catch (FeignException e) {
					e.printStackTrace();
				}
			}
			pedido.setProdutos(pedidosProdutos);
			pedidoRepository.save(pedido);
			URI uri = uriBuilder.path("/pedidos").buildAndExpand(pedido.getId()).toUri();
			return ResponseEntity.created(uri).body(new PedidoDto(pedido));
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<PedidoDto> updatePedido(@PathVariable String id, @RequestBody @Valid PedidoForm form) {
		Optional<Pedido> optional = pedidoRepository.findById(id);
		if (optional.isPresent()) {
			return contaRepository.findById(form.getContaId()).map(conta -> {
				Pedido pedido = optional.get();
				pedido.setConta(conta);
				pedido.setValorTotal(form.getValorTotal());
				pedido.setStatusPedido(form.getStatusPedido());
				pedidoRepository.save(pedido);
				return ResponseEntity.ok(new PedidoDto(pedido));
			}).orElseGet(() -> ResponseEntity.notFound().build());
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletePedido(@PathVariable String id) {
		return pedidoRepository.findById(id).map(pedido -> {
			pedidoRepository.delete(pedido);
			return ResponseEntity.noContent().build();
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}
}
