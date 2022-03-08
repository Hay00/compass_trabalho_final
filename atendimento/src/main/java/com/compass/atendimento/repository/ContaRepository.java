package com.compass.atendimento.repository;

import java.util.List;

import com.compass.atendimento.model.Conta;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends MongoRepository<Conta, String> {
	List<Conta> findByMesaId(String id);
}
