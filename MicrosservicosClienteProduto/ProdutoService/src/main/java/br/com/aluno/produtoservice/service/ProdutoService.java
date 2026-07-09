package br.com.aluno.produtoservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aluno.produtoservice.domain.Produto;
import br.com.aluno.produtoservice.exception.RecursoNaoEncontradoException;
import br.com.aluno.produtoservice.exception.RegraNegocioException;
import br.com.aluno.produtoservice.repository.ProdutoRepository;

/**
 * Regras de negócio do cadastro de Produto.
 */
@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto cadastrar(Produto produto) {
		produtoRepository.findByNome(produto.getNome()).ifPresent(p -> {
			throw new RegraNegocioException("Já existe um produto cadastrado com esse nome.");
		});
		return produtoRepository.save(produto);
	}

	public Produto atualizar(String id, Produto produto) {
		Produto existente = buscarPorId(id);

		produtoRepository.findByNome(produto.getNome()).ifPresent(p -> {
			if (!p.getId().equals(existente.getId())) {
				throw new RegraNegocioException("Já existe outro produto cadastrado com esse nome.");
			}
		});

		produto.setId(existente.getId());
		return produtoRepository.save(produto);
	}

	public Produto buscarPorId(String id) {
		return produtoRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado com o id " + id));
	}

	public List<Produto> pesquisar() {
		return produtoRepository.findAll();
	}

}
