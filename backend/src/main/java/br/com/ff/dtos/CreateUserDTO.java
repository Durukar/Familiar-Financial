package br.com.ff.dtos;

import br.com.ff.models.UserModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public record CreateUserDTO(
		String username,
		String password
) {
}
