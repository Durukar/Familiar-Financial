package br.com.ff.infra.initialization;

import br.com.ff.application.service.FamiliarBalanceService;
import br.com.ff.domain.model.FamiliarBalanceModel;
import br.com.ff.domain.model.UserModel;
import br.com.ff.domain.model.UserRoles;
import br.com.ff.infra.repository.FamiliarBalanceRepository;
import br.com.ff.infra.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Configuration
public class InitialUserConfig implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(InitialUserConfig.class);

	private final UserRepository userRepository;
	private final FamiliarBalanceRepository familiarBalanceRepository;

	private final PasswordEncoder passwordEncoder;

	public InitialUserConfig(UserRepository userRepository, PasswordEncoder passwordEncoder, FamiliarBalanceRepository familiarBalanceRepository, FamiliarBalanceService familiarBalanceService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.familiarBalanceRepository = familiarBalanceRepository;
	}

	@Override
	@Transactional
	public void run(ApplicationArguments args) throws Exception {
		try {

			logger.info("Initializing balance.");

			if (familiarBalanceRepository.findAll().isEmpty()) {
				logger.info("Creating balance...");

				FamiliarBalanceModel balance = new FamiliarBalanceModel(BigDecimal.ZERO);
				familiarBalanceRepository.save(balance);

				logger.info("Balance created!");
			} else {
				logger.info("Balance already exists!");
			}

			logger.info("Initializing internal user...");

			if (userRepository.findByUsername("suporte") == null) {
				logger.info("Creating internal user...");

				UserModel suporte = new UserModel();
				suporte.setUsername("suporte");
				suporte.setPassword(passwordEncoder.encode("ff123"));
				suporte.setRole(UserRoles.valueOf("ROLE_ADMIN"));

				FamiliarBalanceModel balance = familiarBalanceRepository.findAll().get(0);

				if (balance != null) {
					balance.addUser(suporte);
					suporte.setBalanceFamiliar(balance);
					familiarBalanceRepository.save(balance);
					userRepository.save(suporte);
				} else {
					userRepository.save(suporte);
				}

				logger.info("Internal user created!");

			} else {
				logger.info("Internal user already exists!");
			}
		} catch (Exception e) {
			logger.error("Error initializing internal user {}", e.getMessage());
			logger.error("Stacktrace: ", e);
		}
	}
}
