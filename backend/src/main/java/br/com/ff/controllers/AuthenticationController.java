package br.com.ff.controllers;

import br.com.ff.dtos.LoginResponseDTO;
import br.com.ff.dtos.LoginUserDTO;
import br.com.ff.dtos.UserDTO;
import br.com.ff.infra.security.TokenService;
import br.com.ff.models.UserModel;
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

	@PostMapping(path = "/login")
	public ResponseEntity login(@RequestBody @Valid LoginUserDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);

		var token = tokenService.generateToken((UserModel) auth.getPrincipal());

		return ResponseEntity.ok( new LoginResponseDTO(token));
	}
}
