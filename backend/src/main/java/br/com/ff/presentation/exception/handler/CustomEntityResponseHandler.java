package br.com.ff.presentation.exception.handler;

import br.com.ff.presentation.exception.ExceptionResponse;
import br.com.ff.presentation.exception.custom.DuplicateUsernameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomEntityResponseHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(
				new Date(),
				ex.getMessage(),
				request.getDescription(false)
		);

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(DuplicateUsernameException.class)
	public final ResponseEntity<ExceptionResponse> handleConflictExceptions(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(
				new Date(),
				ex.getMessage(),
				request.getDescription(false)
		);

		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}
}
