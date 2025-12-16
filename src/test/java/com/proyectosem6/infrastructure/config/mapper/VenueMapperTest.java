package com.proyectosem6.infrastructure.config.mapper;

import com.proyectosem6.dto.VenueDTO;
import com.proyectosem6.domain.model.Venue;
import com.proyectosem6.entity.VenueEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VenueMapperTest {

	private final VenueMapper mapper = new VenueMapper();

	@Test
	void dto_domain_roundtrip() {
		VenueDTO dto = new VenueDTO(1L, "Sala", "Centro");
		Venue d = mapper.toDomain(dto);
		assertNotNull(d);
		assertEquals(dto.getName(), d.getName());
		VenueDTO out = mapper.toDto(d);
		assertEquals(dto.getLocation(), out.getLocation());
	}

	@Test
	void entity_domain_roundtrip() {
		VenueEntity entity = new VenueEntity("Theater", "Downtown");
		entity.setId(3L);
		Venue d = mapper.toDomain(entity);
		assertNotNull(d);
		assertEquals(entity.getName(), d.getName());
		VenueEntity out = mapper.toEntity(d);
		assertEquals(entity.getLocation(), out.getLocation());
	}

	@Test
	void null_dto_returns_null() {
		assertNull(mapper.toDomain((VenueDTO) null));
	}

	@Test
	void null_entity_returns_null() {
		assertNull(mapper.toDomain((VenueEntity) null));
	}
}
