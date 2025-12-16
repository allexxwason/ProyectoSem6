package com.proyectosem6.infrastructure.config.mapper;

import com.proyectosem6.dto.EventDTO;
import com.proyectosem6.domain.model.Event;
import com.proyectosem6.entity.EventEntity;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

	// DTO ↔ Domain (usados por controladores)
	public Event toDomain(EventDTO dto) {
		if (dto == null) return null;
		Event e = new Event();
		e.setId(dto.getId());
		e.setName(dto.getName());
		e.setDate(dto.getDate());
		e.setVenue(dto.getVenue());
		e.setCategory(dto.getCategory());
		e.setCity(dto.getCity());
		e.setStartDate(dto.getStartDate());
		return e;
	}

	public EventDTO toDto(Event domain) {
		if (domain == null) return null;
		EventDTO dto = new EventDTO();
		dto.setId(domain.getId());
		dto.setName(domain.getName());
		dto.setDate(domain.getDate());
		dto.setVenue(domain.getVenue());
		dto.setCategory(domain.getCategory());
		dto.setCity(domain.getCity());
		dto.setStartDate(domain.getStartDate());
		return dto;
	}

	// Entity ↔ Domain (usados por adaptadores JPA)
	public Event toDomain(EventEntity entity) {
		if (entity == null) return null;
		Event e = new Event();
		e.setId(entity.getId());
		e.setName(entity.getName());
		e.setDate(entity.getDate());
		e.setVenue(entity.getVenue());
		e.setCategory(entity.getCategory());
		e.setCity(entity.getCity());
		e.setStartDate(entity.getStartDate());
		return e;
	}

	public EventEntity toEntity(Event domain) {
		if (domain == null) return null;
		EventEntity entity = new EventEntity();
		entity.setId(domain.getId());
		entity.setName(domain.getName());
		entity.setDate(domain.getDate());
		entity.setVenue(domain.getVenue());
		entity.setCategory(domain.getCategory());
		entity.setCity(domain.getCity());
		entity.setStartDate(domain.getStartDate());
		return entity;
	}
}
