package com.proyectosem6.service;
import com.proyectosem6.dto.EventDTO;
import com.proyectosem6.entity.EventEntity;
import com.proyectosem6.repository.EventRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {
    private final EventRepository repository;

    public EventService(EventRepository repository) { this.repository = repository; }

    public List<EventDTO> getAllEvents() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public Page<EventDTO> getAllEvents(@NonNull Pageable pageable, String venue, String date) {
        if (venue != null && date != null) {
            return repository.findByVenueAndDate(venue, date, pageable).map(this::toDto);
        } else if (venue != null) {
            return repository.findByVenue(venue, pageable).map(this::toDto);
        } else if (date != null) {
            return repository.findByDate(date, pageable).map(this::toDto);
        } else {
            return repository.findAll(pageable).map(this::toDto);
        }
    }

    public Optional<EventDTO> getEventById(@NonNull Long id) { return repository.findById(id).map(this::toDto); }

    public EventDTO createEvent(EventDTO event) {
        try {
            EventEntity e = new EventEntity(event.getName(), event.getDate(), event.getVenue());
            EventEntity saved = repository.save(e);
            return toDto(saved);
        } catch (DataIntegrityViolationException ex) {
            throw ex;
        }
    }

    public Optional<EventDTO> updateEvent(@NonNull Long id, EventDTO updated) {
        return repository.findById(id).map(e -> {
            e.setName(updated.getName());
            e.setDate(updated.getDate());
            e.setVenue(updated.getVenue());
            return toDto(repository.save(e));
        });
    }

    public boolean deleteEvent(@NonNull Long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    private EventDTO toDto(EventEntity e) { return new EventDTO(e.getId(), e.getName(), e.getDate(), e.getVenue()); }
}