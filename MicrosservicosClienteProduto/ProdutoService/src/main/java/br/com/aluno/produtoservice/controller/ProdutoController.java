package br.com.aluno.produtoservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aluno.produtoservice.domain.Produto;
import br.com.aluno.produtoservice.service.ProdutoService;

/**
 * Endpoints REST do microsserviço de Produto:
 * - POST   /produtos      -> cadastrar
 * - PUT    /produtos/{id} -> alterar
 * - GET    /produtos      -> pesquisar todos
 * - GET    /produtos/{id} -> pesquisar por id
 */
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@PostMapping
	public ResponseEntity<Produto> cadastrar(@RequestBody @Valid Produto produto) {
		Produto salvo = produtoService.cadastrar(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizar(@PathVariable String id, @RequestBody @Valid Produto produto) {
		return ResponseEntity.ok(produtoService.atualizar(id, produto));
	}

	@GetMapping
	public ResponseEntity<List<Produto>> pesquisar() {
		return ResponseEntity.ok(produtoService.pesquisar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable String id) {
		return ResponseEntity.ok(produtoService.buscarPorId(id));
	}

}
