package br.com.ff.presentation.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateUsernameException extends RuntimeException {
	public DuplicateUsernameException(String message) {
		super(message);
	}
}
