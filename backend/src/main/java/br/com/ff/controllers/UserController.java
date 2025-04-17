package br.com.ff.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController {

	/*
	 * POST /api/users/create
	 */
	@PostMapping(path = "/create")
	public ResponseEntity<String> create() {
		System.out.println("User created");

		return ResponseEntity.noContent().build();
	}
}
