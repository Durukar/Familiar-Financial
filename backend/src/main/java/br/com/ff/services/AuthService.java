package br.com.ff.services;

import br.com.ff.dtos.LoginUserDTO;
import br.com.ff.models.UserModel;
import br.com.ff.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}


}
