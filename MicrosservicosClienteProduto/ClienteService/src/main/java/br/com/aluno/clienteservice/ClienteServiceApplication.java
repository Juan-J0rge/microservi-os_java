package br.com.aluno.clienteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Microsserviço responsável apenas pelo domínio de Cliente.
 * Cada microsserviço roda de forma independente, na sua própria porta
 * e com o seu próprio banco de dados (ver application.properties).
 */
@SpringBootApplication
public class ClienteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClienteServiceApplication.class, args);
	}

}
