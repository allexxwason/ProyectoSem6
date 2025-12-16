package com.proyectosem6.controller;

import com.proyectosem6.dto.VenueDTO;
import com.proyectosem6.domain.ports.in.VenueUseCase;
import com.proyectosem6.domain.model.Venue;
import com.proyectosem6.infrastructure.config.mapper.VenueMapper;
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
    private final VenueUseCase venueUseCase;
    private final VenueMapper venueMapper;

    public VenueController(VenueUseCase venueUseCase, VenueMapper venueMapper) {
        this.venueUseCase = venueUseCase;
        this.venueMapper = venueMapper;
    }

    @GetMapping
    @Operation(summary = "Listar venues")
    public ResponseEntity<java.util.List<VenueDTO>> getAll() {
        java.util.List<VenueDTO> dtos = venueUseCase.getAllVenues().stream()
                .map(venueMapper::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener venue por id")
    public ResponseEntity<VenueDTO> getById(@PathVariable Long id) {
        return venueUseCase.getVenueById(id)
                .map(venueMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VenueDTO> create(@Valid @RequestBody VenueDTO v) {
        if (v.getName() == null || v.getName().isBlank()) {
            throw new BadRequestException("El nombre del venue es obligatorio");
        }
        Venue venue = venueMapper.toDomain(v);
        Venue created = venueUseCase.createVenue(venue);
        return ResponseEntity.ok(venueMapper.toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VenueDTO> update(@PathVariable Long id, @Valid @RequestBody VenueDTO v) {
        if (v.getName() == null || v.getName().isBlank()) {
            throw new BadRequestException("El nombre del venue es obligatorio");
        }
        Venue venue = venueMapper.toDomain(v);
        return venueUseCase.updateVenue(id, venue)
                .map(venueMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar venue")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return venueUseCase.deleteVenue(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}