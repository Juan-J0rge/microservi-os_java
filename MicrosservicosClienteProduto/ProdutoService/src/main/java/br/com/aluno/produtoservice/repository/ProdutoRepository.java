package br.com.aluno.produtoservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.aluno.produtoservice.domain.Produto;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {

	Optional<Produto> findByNome(String nome);

}
