package com.proyectosem6.controller;

import com.proyectosem6.dto.VenueDTO;
import com.proyectosem6.service.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import com.proyectosem6.exception.BadRequestException;

@RestController
@RequestMapping("/venues")
@Tag(name = "Venues", description = "Endpoints para gestionar venues")
public class VenueController {
    private final VenueService service;
    public VenueController(VenueService service) { this.service = service; }

    @GetMapping
    @Operation(summary = "Listar venues")
    public ResponseEntity<List<VenueDTO>> getAll() { return ResponseEntity.ok(service.getAllVenues()); }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener venue por id")
    public ResponseEntity<VenueDTO> getById(@PathVariable Long id) {
        return service.getVenueById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    @PostMapping
    public ResponseEntity<VenueDTO> create(@Valid @RequestBody VenueDTO v) {
        if (v.getName() == null || v.getName().isBlank()) {
            throw new BadRequestException("El nombre del venue es obligatorio");
        }
        return ResponseEntity.ok(service.createVenue(v));
    }


    @PutMapping("/{id}")
    public ResponseEntity<VenueDTO> update(@PathVariable Long id, @Valid @RequestBody VenueDTO v) {
        if (v.getName() == null || v.getName().isBlank()) {
            throw new BadRequestException("El nombre del venue es obligatorio");
        }
        return service.updateVenue(id, v).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar venue")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.deleteVenue(id)?ResponseEntity.noContent().build():ResponseEntity.notFound().build(); }
}