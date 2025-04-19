package br.com.ff.services;

import br.com.ff.dtos.CreateUserDTO;
import br.com.ff.mappers.UserMapper;
import br.com.ff.models.RoleModel;
import br.com.ff.models.UserModel;
import br.com.ff.projections.UserDetailProjection;
import br.com.ff.repositories.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final UserMapper userMapper;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.userMapper = userMapper;
	}

	public void createUser(CreateUserDTO dto) throws Exception {
		UserModel newUser = userMapper.toModel(dto);
		encryptPassword(newUser);
		userRepository.save(newUser);
	}


	private void encryptPassword(UserModel newUser) {
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserDetailProjection> result = userRepository.searchUserAndRolesByUsername(username);

		if (result.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}

		UserModel user = new UserModel();
		user.setUsername(username);
		user.setPassword(result.get(0).getPassword());
		for (UserDetailProjection projection : result) {
			user.addRole(new RoleModel(projection.getRoleId(), projection.getAuthority()));
		}

		return user;
	}
}
