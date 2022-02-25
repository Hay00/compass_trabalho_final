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

import com.compass.gerenciamento.dto.ProdutoDto;
import com.compass.gerenciamento.form.ProdutoForm;
import com.compass.gerenciamento.model.Produto;
import com.compass.gerenciamento.repository.CardapioRepository;
import com.compass.gerenciamento.repository.ProdutoRepository;

@RestController
@RequestMapping("gerenciamento/produto")
public class ProdutoController {
    
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	CardapioRepository cardapioRepository;
	
	@GetMapping
	public List<ProdutoDto> list() {
		List<Produto> produtos = produtoRepository.findAll();
		return ProdutoDto.convert(produtos);
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "cachedProdutos", allEntries = true)
	public ResponseEntity<ProdutoDto> create(@RequestBody @Valid ProdutoForm form,
			UriComponentsBuilder UriBuilder) {
		
		Produto produto = form.convert();
		produtoRepository.save(produto);
		
		URI uri = UriBuilder.path("/gerenciamento/produto/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProdutoDto(produto));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDto> get(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if(produto.isPresent()) {
			return ResponseEntity.ok(new ProdutoDto(produto.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "cachedProdutos", allEntries = true)
	public ResponseEntity<ProdutoDto> update(@PathVariable Long id, @RequestBody ProdutoForm form) {
			Produto produtoForm = form.convert();
			return produtoRepository.findById(id).map(produto -> {
	                    produto.setNome(produtoForm.getNome());
	                    produto.setDescricao(produtoForm.getDescricao());
	                    produto.setTipo(produtoForm.getTipo());
	                    produto.setStatus(produtoForm.isStatus());
	                    produtoRepository.save(produto);
	                    return ResponseEntity.ok(new ProdutoDto(produto));
	                }).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "cachedProdutos", allEntries = true)
	public ResponseEntity<Object> delete(@PathVariable Long id){
		return produtoRepository.findById(id).map(partido -> {
                    produtoRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
