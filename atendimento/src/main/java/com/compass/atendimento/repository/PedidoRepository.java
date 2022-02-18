package com.compass.atendimento.repository;

import com.compass.atendimento.model.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
