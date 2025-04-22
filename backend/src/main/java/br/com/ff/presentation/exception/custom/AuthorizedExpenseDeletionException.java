package br.com.ff.presentation.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class AuthorizedExpenseDeletionException extends RuntimeException {
	public AuthorizedExpenseDeletionException(String message) {
		super(message);
	}
}
