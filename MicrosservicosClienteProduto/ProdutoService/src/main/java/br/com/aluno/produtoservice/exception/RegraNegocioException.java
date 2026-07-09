package br.com.aluno.produtoservice.exception;

/**
 * Lançada quando uma regra de negócio do cadastro de produto é violada.
 */
public class RegraNegocioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RegraNegocioException(String mensagem) {
		super(mensagem);
	}

}
