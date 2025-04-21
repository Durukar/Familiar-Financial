package br.com.ff.domain.user;

import br.com.ff.application.dto.CreateUserDTO;
import br.com.ff.application.dto.UserDTO;
import br.com.ff.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mapping(target = "role", source = "role")
	UserModel toModel(CreateUserDTO dto);

	UserDTO toDTO(UserModel model);
}
