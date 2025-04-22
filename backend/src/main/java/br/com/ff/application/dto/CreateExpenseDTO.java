package br.com.ff.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateExpenseDTO(
		UUID requestedBy,
		LocalDateTime requestedAt,
		String title,
		String description
) {
}
