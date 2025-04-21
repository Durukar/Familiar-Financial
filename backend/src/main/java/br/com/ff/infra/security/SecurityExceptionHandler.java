package br.com.ff.infra.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.naming.AuthenticationException;
import java.io.IOException;

@Configuration
public abstract class SecurityExceptionHandler {

	@Bean
	public SecurityExceptionHandler securityExceptionHandler() {
		return new SecurityExceptionHandler() {
			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response,
								 AuthenticationException authException) throws IOException {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				response.setContentType("application/json");
				response.getWriter().write("{\"error\": \"" + authException.getMessage() + "\"}");
			}
		};
	}

	public abstract void commence(HttpServletRequest request, HttpServletResponse response,
								  AuthenticationException authException) throws IOException;
}