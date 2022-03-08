package com.compass.atendimento.client;

import java.util.List;

import com.compass.atendimento.dto.ProdutoDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("gerenciamento")
public interface ProdutoClient {

	@RequestMapping(method = RequestMethod.GET, value = "/produtos")
	public List<ProdutoDto> getProdutosByIds(@RequestBody(required = false) List<Long> ids);

	@GetMapping("/produtos/{id}")
	public ProdutoDto getProdutoById(@RequestParam Long id);

	@GetMapping("/produtos")
	public List<ProdutoDto> list(@RequestBody List<Long> ids);
}
