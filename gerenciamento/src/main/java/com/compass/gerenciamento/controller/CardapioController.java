package com.compass.gerenciamento.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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

import com.compass.gerenciamento.dto.CardapioDto;
import com.compass.gerenciamento.form.CardapioForm;
import com.compass.gerenciamento.form.ListaProdutosForm;
import com.compass.gerenciamento.model.Cardapio;
import com.compass.gerenciamento.model.Produto;
import com.compass.gerenciamento.repository.CardapioRepository;
import com.compass.gerenciamento.repository.ProdutoRepository;

@RestController
@RequestMapping("gerenciamento/cardapio")
public class CardapioController {

	@Autowired
	CardapioRepository cardapioRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	@GetMapping
	public List<CardapioDto> list() {
		List<Cardapio> cardapios = cardapioRepository.findAll();
		return CardapioDto.convert(cardapios);
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "cachedProdutos", allEntries = true)
	public ResponseEntity<CardapioDto> create(@RequestBody @Valid CardapioForm form,
			UriComponentsBuilder UriBuilder) {

		Cardapio cardapio = form.convert();
		cardapioRepository.save(cardapio);

		URI uri = UriBuilder.path("/gerenciamento/cardapio/{id}").buildAndExpand(cardapio.getId()).toUri();
		return ResponseEntity.created(uri).body(new CardapioDto(cardapio));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CardapioDto> get(@PathVariable Long id) {
		Optional<Cardapio> cardapio = cardapioRepository.findById(id);
		if (cardapio.isPresent()) {
			return ResponseEntity.ok(new CardapioDto(cardapio.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "cachedProdutos", allEntries = true)
	public ResponseEntity<CardapioDto> update(@PathVariable Long id, @RequestBody CardapioForm form) {
		Cardapio cardapioForm = form.convert();
		return cardapioRepository.findById(id).map(cardapio -> {
			cardapio.setNome(cardapioForm.getNome());
			cardapio.setProdutos(cardapioForm.getProdutos());
			cardapioRepository.save(cardapio);
			return ResponseEntity.ok(new CardapioDto(cardapio));
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "cachedProdutos", allEntries = true)
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		return cardapioRepository.findById(id).map(partido -> {
			cardapioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/{id}/produto")
	@Transactional
	@CacheEvict(value = "cachedCardapios", allEntries = true)
	public ResponseEntity<CardapioDto> vincularProdutoAoCardapio(@PathVariable Long id,
			@RequestBody @Valid ListaProdutosForm form) {
		return cardapioRepository.findById(id).map(cardapio -> {
			List<Produto> produtos = cardapio.getProdutos();
			form.getProdutos().forEach(idProduto -> {
				Optional<Produto> optional = produtoRepository.findById(idProduto);
				if (optional.isPresent()) {
					produtos.add(optional.get());
				}
			});
			cardapio.setProdutos(produtos);
			cardapioRepository.save(cardapio);
			return ResponseEntity.ok(new CardapioDto(cardapio));
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}
}
