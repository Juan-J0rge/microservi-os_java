package br.com.aluno.clienteservice.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entidade Cliente, guardada na coleção "cliente" do MongoDB.
 */
@Document(collection = "cliente")
public class Cliente {

	@Id
	private String id;

	@NotBlank(message = "O nome é obrigatório")
	private String nome;

	@NotNull(message = "O CPF é obrigatório")
	@Indexed(unique = true)
	private Long cpf;

	@NotBlank(message = "O email é obrigatório")
	@Email(message = "Email inválido")
	private String email;

	@NotNull(message = "O telefone é obrigatório")
	private Long telefone;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

}
