package com.compass.gerenciamento.repository;

import com.compass.gerenciamento.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
