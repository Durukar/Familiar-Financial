package br.com.ff.application.service;

import br.com.ff.infra.repository.UserRepository;
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
