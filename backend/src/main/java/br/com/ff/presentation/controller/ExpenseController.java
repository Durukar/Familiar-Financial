package br.com.ff.presentation.controller;

import br.com.ff.application.dto.CreateExpenseDTO;
import br.com.ff.application.dto.ExpenseUpdateDTO;
import br.com.ff.domain.model.ExpenseModel;
import br.com.ff.application.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

	/*
	 *	GET /api/v1/expenses/:id
	 */
	@GetMapping(path = "/{id}")
	public ExpenseModel findById(@PathVariable UUID id) {
		return expenseService.findById(id);
	}


	/*
	 *	POST /api/v1/expenses/add
	 */
	@PostMapping(path = "/add")
	@Transactional
	public Map<String, String> createExpense(@RequestBody CreateExpenseDTO dto) {
		Map<String, String> response = new HashMap<>();

		expenseService.create(dto);
		response.put("message", "Sucess");

		return response;
	}

	/*
	 *	PATCH /api/v1/expenses/revision/:Id
	 */
	@PatchMapping(path = "/revision/{id}")
	public ResponseEntity<ExpenseModel> update(
			@PathVariable UUID id,
			@RequestBody ExpenseUpdateDTO dto) {
		ExpenseModel updateExpense = expenseService.updateStatus(id, dto);
		return ResponseEntity.ok(updateExpense);
	}

	/*
	 *	DELETE /api/v1/exepense/del/:id
	 */
	@DeleteMapping(path = "/del/{id}")
	public ResponseEntity deleteExpense(@Valid @PathVariable("id") UUID id) {

		expenseService.deleteById(id);

		return ResponseEntity.ok().build();
	}
}
