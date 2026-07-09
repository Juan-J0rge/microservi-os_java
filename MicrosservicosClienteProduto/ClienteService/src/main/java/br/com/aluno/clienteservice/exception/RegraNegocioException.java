package br.com.aluno.clienteservice.exception;

/**
 * Lançada quando uma regra de negócio do cadastro de cliente é violada.
 */
public class RegraNegocioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RegraNegocioException(String mensagem) {
		super(mensagem);
	}

}
