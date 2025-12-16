package com.proyectosem6.infrastructure.config.mapper;

import com.proyectosem6.dto.EventDTO;
import com.proyectosem6.domain.model.Event;
import com.proyectosem6.entity.EventEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EventMapperTest {

	private final EventMapper mapper = new EventMapper();

	@Test
	void dto_to_domain_and_back() {
		EventDTO dto = new EventDTO(1L, "Concert", "2025-12-01", "Arena", "Music", "City", LocalDate.of(2025, 12, 1));
		Event domain = mapper.toDomain(dto);
		assertNotNull(domain);
		assertEquals(dto.getName(), domain.getName());
		EventDTO out = mapper.toDto(domain);
		assertEquals(dto.getName(), out.getName());
		assertEquals(dto.getStartDate(), out.getStartDate());
	}

	@Test
	void entity_to_domain_and_back() {
		EventEntity entity = new EventEntity("Show", "2025-11-15", "Hall", "Theater", "NYC", LocalDate.of(2025, 11, 15));
		entity.setId(2L);
		Event domain = mapper.toDomain(entity);
		assertNotNull(domain);
		assertEquals(entity.getName(), domain.getName());
		EventEntity out = mapper.toEntity(domain);
		assertEquals(entity.getName(), out.getName());
		assertEquals(entity.getCity(), out.getCity());
	}

	@Test
	void null_dto_returns_null() {
		assertNull(mapper.toDomain((EventDTO) null));
	}

	@Test
	void null_entity_returns_null() {
		assertNull(mapper.toDomain((EventEntity) null));
	}
}
