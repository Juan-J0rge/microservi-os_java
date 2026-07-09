package br.com.aluno.clienteservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aluno.clienteservice.domain.Cliente;
import br.com.aluno.clienteservice.exception.RecursoNaoEncontradoException;
import br.com.aluno.clienteservice.exception.RegraNegocioException;
import br.com.aluno.clienteservice.repository.ClienteRepository;

/**
 * Regras de negócio do cadastro de Cliente.
 */
@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente cadastrar(Cliente cliente) {
		clienteRepository.findByCpf(cliente.getCpf()).ifPresent(c -> {
			throw new RegraNegocioException("Já existe um cliente cadastrado com esse CPF.");
		});
		return clienteRepository.save(cliente);
	}

	public Cliente atualizar(String id, Cliente cliente) {
		Cliente existente = buscarPorId(id);

		clienteRepository.findByCpf(cliente.getCpf()).ifPresent(c -> {
			if (!c.getId().equals(existente.getId())) {
				throw new RegraNegocioException("Já existe outro cliente cadastrado com esse CPF.");
			}
		});

		cliente.setId(existente.getId());
		return clienteRepository.save(cliente);
	}

	public Cliente buscarPorId(String id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com o id " + id));
	}

	public List<Cliente> pesquisar() {
		return clienteRepository.findAll();
	}

}
