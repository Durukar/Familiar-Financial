package br.com.ff.services;

import br.com.ff.models.ExpenseModel;
import br.com.ff.repositories.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

	private final ExpenseRepository expenseRepository;

	public ExpenseService(ExpenseRepository expenseRepository) {
		this.expenseRepository = expenseRepository;
	}

	public List<ExpenseModel> findAll() {
		return expenseRepository.findAll();
	}
}
