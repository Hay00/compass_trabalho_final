package com.compass.gerenciamento.controller;

import com.compass.gerenciamento.form.UsuarioForm;
import com.compass.gerenciamento.model.Usuario;
import com.compass.gerenciamento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping
	public ResponseEntity<Usuario> create(@RequestBody @Valid UsuarioForm form) {
		Usuario usuario = form.convert();
		usuarioRepository.save(usuario);
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/usuarios/{id}").toUriString());
		return ResponseEntity.created(uri).body(usuario);
	}
}
