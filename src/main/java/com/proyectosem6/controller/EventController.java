package com.proyectosem6.controller;

import com.proyectosem6.dto.EventDTO;
import com.proyectosem6.service.EventService;
import com.proyectosem6.exception.BadRequestException; 


import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping
    public List<EventDTO> getAll() {
        return service.getAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getById(@PathVariable Long id) {
        Optional<EventDTO> event = service.getEventById(id);
        return event.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EventDTO> create(@Valid @RequestBody EventDTO e) {
        if (e.getName() == null || e.getName().isBlank()) {
            throw new BadRequestException("El nombre del evento es obligatorio");
        }
        return ResponseEntity.ok(service.createEvent(e));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> update(@PathVariable Long id, @Valid @RequestBody EventDTO e) {
        if (e.getName() == null || e.getName().isBlank()) {
            throw new BadRequestException("El nombre del evento es obligatorio");
        }
        return service.updateEvent(id, e).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = service.deleteEvent(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
