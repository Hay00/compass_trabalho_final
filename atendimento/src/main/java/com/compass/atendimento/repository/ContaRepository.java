package com.compass.atendimento.repository;

import com.compass.atendimento.model.Conta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {

}
