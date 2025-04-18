package br.com.ff.mappers;

import br.com.ff.dtos.CreateUserDTO;
import br.com.ff.models.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserModel toModel(CreateUserDTO dto);
}
