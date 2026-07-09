package br.com.aluno.clienteservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.aluno.clienteservice.domain.Cliente;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {

	Optional<Cliente> findByCpf(Long cpf);

}
