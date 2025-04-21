package br.com.ff.application.service;

import br.com.ff.application.dto.CreateUserDTO;
import br.com.ff.application.dto.UserDTO;
import br.com.ff.domain.model.UserMapper;
import br.com.ff.domain.model.UserModel;
import br.com.ff.infra.repository.UserRepository;
import br.com.ff.presentation.exception.custom.DuplicateUsernameException;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService{

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final UserMapper userMapper;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.userMapper = userMapper;
	}

	public void	 createUser(CreateUserDTO dto) throws Exception {
		if (userRepository.findByUsername(dto.username()) != null) throw new DuplicateUsernameException("Username already exists");
		UserModel newUser = userMapper.toModel(dto);
		encryptPassword(newUser);
		userRepository.save(newUser);
	}

	public Page<UserDTO> listAll(Pageable pageable) {
		Page<UserModel> users = userRepository.findAll(pageable);
		return users.map(userMapper::toDTO);
	}

	public UserDTO findById(UUID id) {
		UserModel user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(id.toString()));
		return userMapper.toDTO(user);
	}

	public void deleteUserById(UUID id) {
		UserModel user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(id.toString()));
		userRepository.delete(user);
	}

	private void encryptPassword(UserModel newUser) {
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
	}
}
