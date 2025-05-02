package br.com.ff.domain.model;

import br.com.ff.application.dto.BalanceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BalanceMapper {

	@Mapping(target = "user", source = "users")
	BalanceDTO toDTO(FamiliarBalanceModel model);
}
