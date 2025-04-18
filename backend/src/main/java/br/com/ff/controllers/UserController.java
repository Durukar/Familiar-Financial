package br.com.ff.controllers;

import br.com.ff.dtos.CreateUserDTO;
import br.com.ff.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	/*
	 * POST /api/users/create
	 */
	@PostMapping(path = "/create")
	public ResponseEntity<String> create(@RequestBody CreateUserDTO request) throws Exception {
		userService.createUser(request);
		return ResponseEntity.noContent().build();
	}
}
