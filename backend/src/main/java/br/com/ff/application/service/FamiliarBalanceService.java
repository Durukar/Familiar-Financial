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

	public FamiliarBalanceModel create(UserModel user) {

		FamiliarBalanceModel familiarBalance = new FamiliarBalanceModel(BigDecimal.ZERO);

		familiarBalance.addUser(user);

		user.setBalanceFamiliar(familiarBalance);

		return familiarBalanceRepository.save(familiarBalance);
	}
}
