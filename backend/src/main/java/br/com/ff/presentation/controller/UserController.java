package br.com.ff.presentation.controller;

import br.com.ff.application.dto.CreateUserDTO;
import br.com.ff.application.dto.UserDTO;
import br.com.ff.application.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Users", description = "Users routes")
@RestController
@RequestMapping(value = "/users")
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

	/*
	 *	GET /api/users/all
	 */
	@GetMapping(path = "/all")
	public ResponseEntity<Page<UserDTO>> getAll(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sort
	) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		Page<UserDTO> users = userService.listAll(pageable);

		return ResponseEntity.ok(users);
	}

   /*
	*	GET /api/users/:id
	*/
	@GetMapping(path = "/{id}")
	public ResponseEntity<UserDTO> getById(@PathVariable UUID id) throws Exception {
		try {
			var user = userService.findById(id);
			return ResponseEntity.ok(user);
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 *	DELETE /api/users/delete/:id
	 */
	@DeleteMapping(path = "/delete/{id}")
	public void delete(@PathVariable UUID id) throws Exception {
		try {
			userService.deleteUserById(id);
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
	}
}
