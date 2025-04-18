package br.com.ff.services;

import br.com.ff.dtos.CreateUserDTO;
import br.com.ff.mappers.UserMapper;
import br.com.ff.models.UserModel;
import br.com.ff.repositories.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserRepository userRepository;

	private final BCryptPasswordEncoder passwordEncoder;

	private final UserMapper userMapper;

	public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.userMapper = userMapper;
	}

	public final void createUser(CreateUserDTO dto) throws Exception {
		UserModel newUser = userMapper.toModel(dto);
		encryptPassword(newUser);
		userRepository.save(newUser);
	}


	private void encryptPassword(UserModel newUser) {
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
	}
}
