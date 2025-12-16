package com.proyectosem6.controller;

import com.proyectosem6.dto.EventDTO;
import com.proyectosem6.domain.ports.in.EventUseCase;
import com.proyectosem6.infrastructure.config.mapper.EventMapper;
import com.proyectosem6.domain.model.Pagination;
import com.proyectosem6.domain.model.EventFilter;
import com.proyectosem6.domain.model.Event;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;

@RestController
@RequestMapping("/events")
@Tag(name = "Events", description = "Endpoints para gestionar eventos")
public class EventController {

    private final EventUseCase eventUseCase;
    private final EventMapper eventMapper;

    public EventController(EventUseCase eventUseCase, EventMapper eventMapper) {
        this.eventUseCase = eventUseCase;
        this.eventMapper = eventMapper;
    }

    @GetMapping
    @Operation(summary = "Listar eventos con paginación y filtros")
    public Page<EventDTO> getAllEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate
    ) {
        Pagination pagination = new Pagination(page, size, sort[0] + (sort.length > 1 ? "," + sort[1] : ""));
        EventFilter filter = new EventFilter(city, category, startDate);
        return eventUseCase.findAll(pagination, filter)
                .map(eventMapper::toDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener evento por id")
    public ResponseEntity<EventDTO> getById(@PathVariable Long id) {
        return eventUseCase.getEventById(id)
                .map(eventMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear nuevo evento")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EventDTO.class),
                    examples = @ExampleObject(
                            value = "{\"name\":\"Concierto\",\"date\":\"2025-12-01\",\"venue\":\"Arena\", \"category\":\"Music\", \"city\":\"Bogotá\", \"startDate\":\"2025-12-01\"}"
                    )
            )
    )
    public ResponseEntity<EventDTO> create(@Valid @RequestBody EventDTO dto) {
        Event event = eventMapper.toDomain(dto);
        Event created = eventUseCase.createEvent(event);
        return ResponseEntity.ok(eventMapper.toDto(created));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar evento")
    public ResponseEntity<EventDTO> update(@PathVariable Long id, @Valid @RequestBody EventDTO dto) {
        Event event = eventMapper.toDomain(dto);
        return eventUseCase.updateEvent(id, event)
                .map(eventMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar evento")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return eventUseCase.deleteEvent(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
