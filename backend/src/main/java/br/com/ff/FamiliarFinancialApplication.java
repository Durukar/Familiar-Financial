package br.com.ff;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;


@OpenAPIDefinition(
		info = @Info(
				title = "Familiar Financial API",
				version = "1.0",
				description = "API of Familiar Financial Project"
		)
)
@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class FamiliarFinancialApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamiliarFinancialApplication.class, args);
	}

}
