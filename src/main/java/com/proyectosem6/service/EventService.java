package com.proyectosem6.service;

import com.proyectosem6.dto.EventDTO;
import com.proyectosem6.entity.EventEntity;
import com.proyectosem6.exception.ConflictException;
import com.proyectosem6.repository.EventRepository;
import com.proyectosem6.repository.EventSpecification;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.stereotype.Service;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    // NUEVO MÉTODO PRINCIPAL (HU2 – PAGINACIÓN + FILTROS)
    public Page<EventDTO> findAll(
            Pageable pageable,
            String city,
            String category,
            LocalDate startDate
    ) {
        Specification<EventEntity> spec = EventSpecification.filterBy(city, category, startDate);
        return repository.findAll(spec, pageable).map(this::toDto);
    }

    // CRUD BASE
    public Optional<EventDTO> getEventById(@NonNull Long id) {
        return repository.findById(id).map(this::toDto);
    }

    public EventDTO createEvent(EventDTO event) {
        try {
            EventEntity entity = new EventEntity(
                    event.getName(),
                    event.getDate(),
                    event.getVenue(),
                    event.getCategory(),
                    event.getCity(),
                    event.getStartDate()
            );
            EventEntity saved = repository.save(entity);
            return toDto(saved);

        } catch (DataIntegrityViolationException ex) {
            throw new ConflictException("Ya existe un evento con ese nombre.");
        }
    }

    public Optional<EventDTO> updateEvent(@NonNull Long id, EventDTO updated) {
        return repository.findById(id).map(e -> {
            e.setName(updated.getName());
            e.setDate(updated.getDate());
            e.setVenue(updated.getVenue());
            e.setCategory(updated.getCategory());
            e.setCity(updated.getCity());
            e.setStartDate(updated.getStartDate());
            return toDto(repository.save(e));
        });
    }

    public boolean deleteEvent(@NonNull Long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    // MAPPER INTERNO
    private EventDTO toDto(EventEntity e) {
        return new EventDTO(
                e.getId(),
                e.getName(),
                e.getDate(),
                e.getVenue(),
                e.getCategory(),
                e.getCity(),
                e.getStartDate()
        );
    }
}
