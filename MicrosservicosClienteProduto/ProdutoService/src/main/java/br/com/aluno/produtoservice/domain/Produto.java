package br.com.aluno.produtoservice.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entidade Produto, guardada na coleção "produto" do MongoDB.
 */
@Document(collection = "produto")
public class Produto {

	@Id
	private String id;

	@NotBlank(message = "O nome é obrigatório")
	@Indexed(unique = true)
	private String nome;

	private String descricao;

	@NotNull(message = "O preço é obrigatório")
	@Positive(message = "O preço deve ser maior que zero")
	private Double preco;

	@NotNull(message = "A quantidade é obrigatória")
	@PositiveOrZero(message = "A quantidade não pode ser negativa")
	private Integer quantidade;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
