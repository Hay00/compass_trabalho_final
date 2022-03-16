package com.compass.gerenciamento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compass.gerenciamento.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);
}
