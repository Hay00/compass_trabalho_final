package com.compass.atendimento.service;

import java.math.BigDecimal;
import java.util.List;

import com.compass.atendimento.form.ProdutosForm;
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
	public Pedido save(Pedido pedido, String contaId, List<ProdutosForm> formList) {
		Long[] produtosIds = formList.stream().map(ProdutosForm::getId).toArray(Long[]::new);
		return contaRepository.findById(contaId).map(conta -> {
			List<ProdutoPedido> produtos = ProdutoPedido.converter(produtoService.findAllByIds(produtosIds));
			// Adiciona quantidade de cada produto
			produtos.forEach(produto -> produto.setQuantidade(formList.stream()
					.filter(p -> p.getId().equals(produto.getId())).findFirst().get().getQuantidade()));
			pedido.setProdutos(produtos);
			pedido.setValorTotal(this.sumProdutos(produtos));
			pedido.setConta(conta);
			pedidoRepository.save(pedido);
			conta.getPedidos().add(pedido);
			contaRepository.save(conta);
			return pedido;
		}).orElseThrow(() -> new IllegalArgumentException(CONTA_NOT_FOUND));
	}

	@Override
	public Pedido update(String id, Pedido newPedido, List<ProdutosForm> formList, String contaId) {
		Long[] produtosIds = formList.stream().map(ProdutosForm::getId).toArray(Long[]::new);
		return pedidoRepository.findById(id).map(pedido -> {
			List<ProdutoPedido> produtos = ProdutoPedido.converter(produtoService.findAllByIds(produtosIds));
			// Adiciona quantidade de cada produto
			produtos.forEach(produto -> produto.setQuantidade(formList.stream()
					.filter(p -> p.getId().equals(produto.getId())).findFirst().get().getQuantidade()));
			pedido.setProdutos(produtos);
			pedido.setValorTotal(this.sumProdutos(produtos));
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

	private BigDecimal sumProdutos(List<ProdutoPedido> produtos) {
		return produtos.stream().map(p -> p.getPreco().multiply(new BigDecimal(p.getQuantidade())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
