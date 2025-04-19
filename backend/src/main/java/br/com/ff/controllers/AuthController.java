package br.com.ff.controllers;

import br.com.ff.dtos.LoginUserDTO;
import br.com.ff.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	/*
	*	POST /api/v1/auth/pass
	*/
	@PostMapping(path = "/pass")
	public ResponseEntity<String> passValidate(@RequestBody LoginUserDTO credentials) {

		authService.passworValidate(credentials);

		return ResponseEntity.noContent().build();
	}
}
