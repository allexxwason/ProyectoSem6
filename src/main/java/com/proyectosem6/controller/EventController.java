package com.proyectosem6.controller;

import com.proyectosem6.dto.EventDTO;
import com.proyectosem6.service.EventService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.lang.NonNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;

@RestController
@RequestMapping("/events")
@Tag(name = "Events", description = "Endpoints para gestionar eventos")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    // MÉTODO PRINCIPAL (HU2)
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

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(
                        Sort.Order.by(sort[0]).with(
                                sort.length > 1 && sort[1].equalsIgnoreCase("desc")
                                        ? Sort.Direction.DESC
                                        : Sort.Direction.ASC
                        )
                )
        );

        return service.findAll(pageable, city, category, startDate);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener evento por id")
    public ResponseEntity<EventDTO> getById(@NonNull @PathVariable Long id) {
        return service.getEventById(id)
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
        return ResponseEntity.ok(service.createEvent(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar evento")
    public ResponseEntity<EventDTO> update(
            @NonNull @PathVariable Long id,
            @Valid @RequestBody EventDTO dto
    ) {
        return service.updateEvent(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar evento")
    public ResponseEntity<Void> delete(@NonNull @PathVariable Long id) {
        return service.deleteEvent(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
