package br.com.ff.application.service;

import br.com.ff.application.dto.BalanceDTO;
import br.com.ff.domain.model.BalanceMapper;
import br.com.ff.domain.model.FamiliarBalanceModel;
import br.com.ff.domain.model.UserModel;
import br.com.ff.infra.repository.FamiliarBalanceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FamiliarBalanceService {

	private final FamiliarBalanceRepository familiarBalanceRepository;
	private final BalanceMapper balanceMapper;

	public FamiliarBalanceService(FamiliarBalanceRepository familiarBalanceRepository, BalanceMapper balanceMapper) {
		this.familiarBalanceRepository = familiarBalanceRepository;
		this.balanceMapper = balanceMapper;
	}

	public List<BalanceDTO> listAll() {
		return familiarBalanceRepository.findAll().stream()
				.map(balanceMapper::toDTO)
				.collect(Collectors.toList());
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
