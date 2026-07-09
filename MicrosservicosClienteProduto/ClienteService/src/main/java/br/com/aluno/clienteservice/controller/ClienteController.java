package br.com.aluno.clienteservice.controller;

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

import br.com.aluno.clienteservice.domain.Cliente;
import br.com.aluno.clienteservice.service.ClienteService;

/**
 * Endpoints REST do microsserviço de Cliente:
 * - POST   /clientes      -> cadastrar
 * - PUT    /clientes/{id} -> alterar
 * - GET    /clientes      -> pesquisar todos
 * - GET    /clientes/{id} -> pesquisar por id
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@PostMapping
	public ResponseEntity<Cliente> cadastrar(@RequestBody @Valid Cliente cliente) {
		Cliente salvo = clienteService.cadastrar(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable String id, @RequestBody @Valid Cliente cliente) {
		return ResponseEntity.ok(clienteService.atualizar(id, cliente));
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> pesquisar() {
		return ResponseEntity.ok(clienteService.pesquisar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable String id) {
		return ResponseEntity.ok(clienteService.buscarPorId(id));
	}

}
