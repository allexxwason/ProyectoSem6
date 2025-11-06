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

@RestController
@RequestMapping("/events")
@Tag(name = "Events", description = "Endpoints para gestionar eventos")
public class EventController {
    private final EventService service;
    public EventController(EventService service) { this.service = service; }

    @GetMapping
    @Operation(summary = "Listar eventos")
    public ResponseEntity<List<EventDTO>> getAll() { return ResponseEntity.ok(service.getAllEvents()); }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener evento por id")
    public ResponseEntity<EventDTO> getById(@PathVariable Long id) {
        return service.getEventById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    @PostMapping
    @Operation(summary = "Crear nuevo evento")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventDTO.class), examples = @ExampleObject(value = "{\"name\":\"Concierto\",\"date\":\"2025-12-01\",\"venue\":\"Arena\"}")))
    public ResponseEntity<EventDTO> create(@Valid @RequestBody EventDTO e) {
        return ResponseEntity.ok(service.createEvent(e)); }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar evento")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventDTO.class), examples = @ExampleObject(value = "{\"name\":\"Concierto Updated\",\"date\":\"2025-12-02\",\"venue\":\"Arena\"}")))
    public ResponseEntity<EventDTO> update(@PathVariable Long id, @Valid @RequestBody EventDTO e) {
        return service.updateEvent(id,e).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar evento")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.deleteEvent(id)?ResponseEntity.noContent().build():ResponseEntity.notFound().build(); }
}