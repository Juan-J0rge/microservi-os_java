package br.com.aluno.produtoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Microsserviço responsável apenas pelo domínio de Produto.
 * Independente do ClienteService: roda na sua própria porta e com
 * o seu próprio banco de dados (ver application.properties).
 */
@SpringBootApplication
public class ProdutoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdutoServiceApplication.class, args);
	}

}
