package br.com.ff.presentation.controller;

import br.com.ff.application.service.FamiliarBalanceService;
import br.com.ff.domain.model.FamiliarBalanceModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/fb")
public class FamiliarBalanceController {

	private final FamiliarBalanceService familiarBalanceService;

	public FamiliarBalanceController(FamiliarBalanceService familiarBalanceService) {
		this.familiarBalanceService = familiarBalanceService;
	}

	/*
	 *	GET /api/v1/fb/all
	 */
	@GetMapping(path = "/all")
	public List<FamiliarBalanceModel> listAll() {
		return familiarBalanceService.listAll();
	}
}
