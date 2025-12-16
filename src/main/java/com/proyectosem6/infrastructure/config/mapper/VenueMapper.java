package com.proyectosem6.infrastructure.config.mapper;

import com.proyectosem6.dto.VenueDTO;
import com.proyectosem6.domain.model.Venue;
import com.proyectosem6.entity.VenueEntity;
import org.springframework.stereotype.Component;

@Component
public class VenueMapper {

	// DTO ↔ Domain
	public Venue toDomain(VenueDTO dto) {
		if (dto == null) return null;
		return new Venue(dto.getId(), dto.getName(), dto.getLocation());
	}

	public VenueDTO toDto(Venue domain) {
		if (domain == null) return null;
		return new VenueDTO(domain.getId(), domain.getName(), domain.getLocation());
	}

	// Entity ↔ Domain
	public Venue toDomain(VenueEntity entity) {
		if (entity == null) return null;
		return new Venue(entity.getId(), entity.getName(), entity.getLocation());
	}

	public VenueEntity toEntity(Venue domain) {
		if (domain == null) return null;
		VenueEntity entity = new VenueEntity();
		entity.setId(domain.getId());
		entity.setName(domain.getName());
		entity.setLocation(domain.getLocation());
		return entity;
	}
}
