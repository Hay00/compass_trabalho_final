package com.compass.atendimento.repository;

import com.compass.atendimento.model.Pedido;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends MongoRepository<Pedido, String> {

}
