package com.compass.atendimento.repository;

import com.compass.atendimento.model.Mesa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesaRepository extends MongoRepository<Mesa, String> {

}
