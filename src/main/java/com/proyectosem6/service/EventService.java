package com.proyectosem6.service;

import com.proyectosem6.dto.EventDTO;
import com.proyectosem6.entity.EventEntity;
import com.proyectosem6.exception.ConflictException;
import com.proyectosem6.repository.EventRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {
    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public List<EventDTO> getAllEvents() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public Optional<EventDTO> getEventById(Long id) {
        return repository.findById(id).map(this::toDto);
    }

    public EventDTO createEvent(EventDTO event) {
        if (repository.findByNameIgnoreCase(event.getName()).isPresent()) {
            throw new ConflictException("Ya existe un evento con el nombre: " + event.getName());
        }

        try {
            EventEntity e = new EventEntity(event.getName(), event.getDate(), event.getVenue(), event.getCategory());
            EventEntity saved = repository.save(e);
            return toDto(saved);
        } catch (DataIntegrityViolationException ex) {
            throw new ConflictException("Error al crear el evento: nombre duplicado");
        }
    }

    public Optional<EventDTO> updateEvent(Long id, EventDTO updated) {
        return repository.findById(id).map(e -> {
            e.setName(updated.getName());
            e.setDate(updated.getDate());
            e.setVenue(updated.getVenue());
            e.setCategory(updated.getCategory());
            return toDto(repository.save(e));
        });
    }

    public boolean deleteEvent(Long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    private EventDTO toDto(EventEntity e) {
        return new EventDTO(e.getId(), e.getName(), e.getDate(), e.getVenue(), e.getCategory());
    }
}
