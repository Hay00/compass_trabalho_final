package com.compass.atendimento.service;

import java.util.List;

import com.compass.atendimento.client.ProdutoClient;
import com.compass.atendimento.dto.ProdutoDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoClient produtoClient;

	public List<ProdutoDto> getProdutosByIds(List<Long> ids) {
		return produtoClient.getProdutosByIds(ids);
	}

	public ProdutoDto getProdutoById(Long id) {
		return produtoClient.getProdutoById(id);
	}
}
