package com.proyectosem6.infrastructure.config;

import com.proyectosem6.application.usecase.EventUseCaseImpl;
import com.proyectosem6.application.usecase.VenueUseCaseImpl;
import com.proyectosem6.domain.ports.in.EventUseCase;
import com.proyectosem6.domain.ports.in.VenueUseCase;
import com.proyectosem6.domain.ports.out.EventRepositoryPort;
import com.proyectosem6.domain.ports.out.VenueRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HexagonalConfig {

	@Bean
	public EventUseCase eventUseCase(EventRepositoryPort port) {
		return new EventUseCaseImpl(port);
	}

	@Bean
	public VenueUseCase venueUseCase(VenueRepositoryPort port) {
		return new VenueUseCaseImpl(port);
	}
}
