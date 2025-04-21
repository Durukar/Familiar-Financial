package br.com.ff.application.dto;

import br.com.ff.domain.model.UserRoles;

import java.util.UUID;

public record UserDTO(
		UUID id,
		String username,
		String password,
		UserRoles role
) {
}
