package br.com.ff.controllers;

import br.com.ff.models.ExpenseModel;
import br.com.ff.services.ExpenseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/expenses")
public class ExpenseController {


	private final ExpenseService expenseService;

	public ExpenseController(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}

	/*
	*	GET /api/v1/expenses/all
	*/
	@GetMapping(path = "/all")
	public List<ExpenseModel> listAll() {
		return expenseService.findAll();
	}
}
