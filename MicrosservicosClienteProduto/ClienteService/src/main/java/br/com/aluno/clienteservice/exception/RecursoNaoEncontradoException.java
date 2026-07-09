package br.com.aluno.clienteservice.exception;

/**
 * Lançada quando o cliente pesquisado não existe na base.
 */
public class RecursoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RecursoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

}
