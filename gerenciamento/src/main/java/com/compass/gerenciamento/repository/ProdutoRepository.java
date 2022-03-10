package com.compass.gerenciamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.compass.gerenciamento.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query("select p from Produto p where p.id in :ids")
    List<Produto> findAllById(Long[] ids);
}
