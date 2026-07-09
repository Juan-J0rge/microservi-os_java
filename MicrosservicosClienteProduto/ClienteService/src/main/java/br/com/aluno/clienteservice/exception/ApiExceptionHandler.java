package br.com.aluno.clienteservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Trata as exceções da API e devolve uma resposta HTTP com uma mensagem clara,
 * em vez de estourar um erro 500 genérico pro cliente da API.
 */
@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public ResponseEntity<String> handleNaoEncontrado(RecursoNaoEncontradoException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler(RegraNegocioException.class)
	public ResponseEntity<String> handleRegraNegocio(RegraNegocioException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidacao(MethodArgumentNotValidException ex) {
		String mensagem = ex.getBindingResult().getFieldErrors().stream()
				.map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
				.reduce((a, b) -> a + "; " + b)
				.orElse("Dados inválidos");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagem);
	}

}
