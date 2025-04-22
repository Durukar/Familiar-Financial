package br.com.ff.infra.initialization;

import br.com.ff.domain.model.UserModel;
import br.com.ff.domain.model.UserRoles;
import br.com.ff.infra.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InitialUserConfig implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(InitialUserConfig.class);

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	public InitialUserConfig(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		try {
			logger.info("Initializing internal user...");

			if (userRepository.findByUsername("suporte") == null) {
				logger.info("Creating internal user...");

				UserModel suporte = new UserModel();
				suporte.setUsername("suporte");
				suporte.setPassword(passwordEncoder.encode("ff123"));
				suporte.setRole(UserRoles.valueOf("ROLE_ADMIN"));

				userRepository.save(suporte);

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
