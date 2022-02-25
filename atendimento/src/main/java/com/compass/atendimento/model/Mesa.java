package com.compass.atendimento.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mesa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String numero;

	@NotNull
	private int capacidade;

	private boolean ocupada = false;

	@OneToMany(mappedBy = "mesa")
	private List<Conta> contas;

	public Mesa(String numero, int capacidade, boolean status) {
		this.numero = numero;
		this.capacidade = capacidade;
		this.ocupada = status;
		this.contas = new ArrayList<>();
	}
}
