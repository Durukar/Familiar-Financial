package br.com.ff.application.service;

import br.com.ff.domain.model.ExpenseModel;
import br.com.ff.infra.repository.ExpenseRepository;
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
