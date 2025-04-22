package br.com.ff.domain.model;

import br.com.ff.application.dto.CreateExpenseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

	@Mapping(target = "requestedBy", source = "user")
	ExpenseModel toModel(CreateExpenseDTO dto, UserModel user);
}
