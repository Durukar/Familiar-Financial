package br.com.ff.application.dto;

import br.com.ff.domain.model.UserRoles;

public record CreateUserDTO(
		String username,
		String password,
		UserRoles role
) {
}
