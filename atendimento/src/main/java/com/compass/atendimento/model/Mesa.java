package com.compass.atendimento.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "mesa")
public class Mesa {

	@Id
	private String id;

	private String numero;

	private int capacidade;

	private boolean ocupada = false;

	@DBRef(db = "conta")
	private List<Conta> contas;

	public Mesa(String numero, int capacidade, boolean status) {
		this.numero = numero;
		this.capacidade = capacidade;
		this.ocupada = status;
		this.contas = new ArrayList<>();
	}
}
