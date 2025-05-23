package br.com.ff.presentation.controller;

import br.com.ff.application.dto.LoginResponseDTO;
import br.com.ff.application.dto.LoginUserDTO;
import br.com.ff.infra.security.TokenService;
import br.com.ff.domain.model.UserModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication", description = "Authentication routes")
@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;

	public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}

	/*
	 *	POST /api/v1/auth/login
	 */
	@PostMapping(path = "/login")
	public ResponseEntity login(@RequestBody @Valid LoginUserDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);

		var token = tokenService.generateToken((UserModel) auth.getPrincipal());

		return ResponseEntity.ok( new LoginResponseDTO(token));
	}


}
