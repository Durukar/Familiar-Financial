package br.com.ff.application.dto;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

public record BalanceDTO(
	UUID id,
	BigDecimal balance,
	Set<UserDTO> user
) {
}
