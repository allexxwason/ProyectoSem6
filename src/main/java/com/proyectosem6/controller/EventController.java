package com.proyectosem6.controller;

import com.proyectosem6.dto.EventDTO;
import com.proyectosem6.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

@RestController
@RequestMapping("/events")
@Tag(name = "Events", description = "Endpoints para gestionar eventos")
public class EventController {
    private final EventService service;
    public EventController(EventService service) { this.service = service; }

    @GetMapping
    @Operation(summary = "Listar eventos con paginación y filtros")
    public ResponseEntity<Page<EventDTO>> getAll(@NonNull Pageable pageable,
        @RequestParam(required = false) String venue,
        @RequestParam(required = false) String date) {
        Page<EventDTO> page = service.getAllEvents(pageable, venue, date);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener evento por id")
    public ResponseEntity<EventDTO> getById(@NonNull @PathVariable Long id) {
        return service.getEventById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    @PostMapping
    @Operation(summary = "Crear nuevo evento")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventDTO.class), examples = @ExampleObject(value = "{\"name\":\"Concierto\",\"date\":\"2025-12-01\",\"venue\":\"Arena\"}")))
    public ResponseEntity<EventDTO> create(@Valid @RequestBody EventDTO e) {
        return ResponseEntity.ok(service.createEvent(e)); }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar evento")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventDTO.class), examples = @ExampleObject(value = "{\"name\":\"Concierto Updated\",\"date\":\"2025-12-02\",\"venue\":\"Arena\"}")))
    public ResponseEntity<EventDTO> update(@NonNull @PathVariable Long id, @Valid @RequestBody EventDTO e) {
        return service.updateEvent(id,e).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar evento")
    public ResponseEntity<Void> delete(@NonNull @PathVariable Long id) {
        return service.deleteEvent(id)?ResponseEntity.noContent().build():ResponseEntity.notFound().build(); }
}