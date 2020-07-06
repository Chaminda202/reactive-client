package com.reactive.spring;

import com.reactive.spring.config.ApplicationProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@AllArgsConstructor
public class ReactiveClientApplication {
	private final ApplicationProperties applicationProperties;
	public static void main(String[] args) {
		SpringApplication.run(ReactiveClientApplication.class, args);
	}

	@Bean
	WebClient webClient() {
		return WebClient.create(this.applicationProperties.getEmployeeEndpoint().getSpringMvc());
	}
}
