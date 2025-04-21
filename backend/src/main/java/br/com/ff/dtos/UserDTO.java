package br.com.ff.dtos;

import br.com.ff.enums.UserRoles;

import java.util.UUID;

public record UserDTO(
		UUID id,
		String username,
		String password,
		UserRoles role
) {
}
