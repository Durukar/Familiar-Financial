package br.com.ff.application.service;

import br.com.ff.domain.model.FamiliarBalanceModel;
import br.com.ff.domain.model.UserModel;
import br.com.ff.infra.repository.FamiliarBalanceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FamiliarBalanceService {

	private final FamiliarBalanceRepository familiarBalanceRepository;

	public FamiliarBalanceService(FamiliarBalanceRepository familiarBalanceRepository) {
		this.familiarBalanceRepository = familiarBalanceRepository;
	}

	public List<FamiliarBalanceModel> listAll() {
		return familiarBalanceRepository.findAll();
	}

	// De acordo com a regra de negocios o sistema ja deve possui um balance na inicialização
	// da aplicação
	public void update(UserModel user) {

		FamiliarBalanceModel balance = familiarBalanceRepository.findAll().get(0);

		balance.addUser(user);

		user.setBalanceFamiliar(balance);

		familiarBalanceRepository.save(balance);
	}
}
