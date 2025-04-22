package br.com.ff.domain.model;

import br.com.ff.application.dto.CreateUserDTO;
import br.com.ff.application.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mapping(target = "role", source = "role")
	UserModel toModel(CreateUserDTO dto);

	UserDTO toDTO(UserModel model);
}
