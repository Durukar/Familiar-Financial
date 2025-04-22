package br.com.ff.application.dto;

import br.com.ff.domain.model.ExpenseStatus;

public record ExpenseUpdateDTO(
		ExpenseStatus status
) {
}
