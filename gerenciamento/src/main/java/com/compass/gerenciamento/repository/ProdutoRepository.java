package com.compass.gerenciamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compass.gerenciamento.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	

}
