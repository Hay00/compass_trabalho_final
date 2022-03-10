package com.compass.atendimento.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.compass.atendimento.model.Pedido;
import com.compass.atendimento.model.ProdutoPedido;
import com.compass.atendimento.repository.ContaRepository;
import com.compass.atendimento.repository.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

	private static final String CONTA_NOT_FOUND = "Conta não encontrada";

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private ProdutoService produtoService;

	@Override
	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

	@Override
	public Pedido findById(String id) {
		return pedidoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
	}

	@Override
	public Pedido save(Pedido pedido, String contaId, Long[] produtosIds) {
		return contaRepository.findById(contaId).map(conta -> {
			pedido.setProdutos(ProdutoPedido.converter(produtoService.findAllByIds(produtosIds)));
			pedido.setConta(conta);
			pedidoRepository.save(pedido);
			conta.getPedidos().add(pedido);
			contaRepository.save(conta);
			return pedido;
		}).orElseThrow(() -> new IllegalArgumentException(CONTA_NOT_FOUND));
	}

	@Override
	public Pedido update(String id, Pedido newPedido, Long[] produtosIds, String contaId) {
		return pedidoRepository.findById(id).map(pedido -> {
			try {
				List<ProdutoPedido> produtos = ProdutoPedido.converter(produtoService.findAllByIds(produtosIds));
				pedido.setProdutos(produtos);
				BigDecimal valorTotal = new BigDecimal(0);
				for (ProdutoPedido p : produtos) {
					valorTotal = valorTotal.add(p.getPreco().multiply(BigDecimal.valueOf(p.getQuantidade())));
				}
				pedido.setValorTotal(valorTotal);
			} catch (Exception e) {
				pedido.setProdutos(new ArrayList<>());
			}
			pedido.setStatusPedido(newPedido.getStatusPedido());

			// Validando se a conta do pedido continua a mesma
			if (pedido.getId().equals(contaId)) {
				return contaRepository.findById(contaId).map(conta -> pedidoRepository.save(pedido))
						.orElseThrow(() -> new IllegalArgumentException(CONTA_NOT_FOUND));
			}

			// Remove referencia antiga a conta
			contaRepository.findById(contaId).ifPresent(oldConta -> {
				oldConta.getPedidos().removeIf(p -> p.getId().equals(pedido.getId()));
				contaRepository.save(oldConta);
			});

			// Adiciona referencia nova
			return contaRepository.findById(contaId).map(conta -> {
				pedido.setConta(conta);
				pedidoRepository.save(pedido);
				conta.getPedidos().add(pedido);
				contaRepository.save(conta);
				return pedido;
			}).orElseThrow(() -> new IllegalArgumentException(CONTA_NOT_FOUND));
		}).orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
	}

	@Override
	public void delete(String id) {
		pedidoRepository.findById(id).ifPresent(pedido -> pedidoRepository.delete(pedido));
	}

}
