package br.com.ff.application.service;

import br.com.ff.application.dto.CreateExpenseDTO;
import br.com.ff.application.dto.ExpenseUpdateDTO;
import br.com.ff.domain.model.ExpenseModel;
import br.com.ff.domain.model.ExpenseStatus;
import br.com.ff.domain.model.UserModel;
import br.com.ff.infra.repository.ExpenseRepository;
import br.com.ff.infra.repository.UserRepository;
import br.com.ff.presentation.exception.custom.AuthorizedExpenseDeletionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ExpenseService {

	private final ExpenseRepository expenseRepository;
	private final UserRepository userRepository;

	public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
		this.expenseRepository = expenseRepository;
		this.userRepository = userRepository;
	}

	@Transactional(readOnly = true)
	public List<ExpenseModel> findAll() {
		return expenseRepository.findAll();
	}

	@Transactional
	public void create(CreateExpenseDTO dto) {
		UserModel user = userRepository.findById(dto.requestedBy()).orElseThrow(() -> new RuntimeException("User not found"));
		ExpenseModel newExpense = new ExpenseModel();

		updateExpenseFromDTO(dto, newExpense, user);

		expenseRepository.save(newExpense);
	}

	public void deleteById(UUID id) {
		ExpenseModel expense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));
		if (expense.getStatus() == ExpenseStatus.APROVED) {
			throw new AuthorizedExpenseDeletionException("Expense is already aproved.");
		}

		expenseRepository.delete(expense);
	}

	public ExpenseModel findById(UUID id) {

		return expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));
	}

	public ExpenseModel updateStatus(UUID id, ExpenseUpdateDTO dto) {
		ExpenseModel expense = findById(id);

		if (expense.getStatus() == ExpenseStatus.APROVED) {
			throw new AuthorizedExpenseDeletionException("Expense is already aproved.");
		}

		expense.setStatus(dto.status());

		return expenseRepository.save(expense);
	}

	// Helpers and Custom Methods

	private void updateExpenseFromDTO(CreateExpenseDTO dto, ExpenseModel expenseModel, UserModel userModel) {
		expenseModel.setRequestedBy(userModel);
		expenseModel.setRequestedAt(dto.requestedAt());
		expenseModel.setTitle(dto.title());
		expenseModel.setDescription(dto.description());
		expenseModel.setStatus(ExpenseStatus.PENDING);
	}
}
