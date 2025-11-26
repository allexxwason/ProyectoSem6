package com.proyectosem6.service;

import com.proyectosem6.dto.VenueDTO;
import com.proyectosem6.entity.VenueEntity;
import com.proyectosem6.exception.ConflictException;
import com.proyectosem6.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VenueService {
    private final VenueRepository repository;

    public VenueService(VenueRepository repository) { this.repository = repository; }

    public List<VenueDTO> getAllVenues() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public Optional<VenueDTO> getVenueById(Long id) { return repository.findById(id).map(this::toDto); }

    public VenueDTO createVenue(VenueDTO venue) {
        if (repository.findByNameIgnoreCase(venue.getName()).isPresent()) {
            throw new ConflictException("Ya existe un venue con el nombre: " + venue.getName());
        }
    
        VenueEntity e = new VenueEntity(venue.getName(), venue.getLocation());
        VenueEntity saved = repository.save(e);
        return toDto(saved);
    }

    public Optional<VenueDTO> updateVenue(Long id, VenueDTO updated) {
        return repository.findById(id).map(v -> {
            v.setName(updated.getName());
            v.setLocation(updated.getLocation());
            return toDto(repository.save(v));
        });
    }

    public boolean deleteVenue(Long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    private VenueDTO toDto(VenueEntity v) { return new VenueDTO(v.getId(), v.getName(), v.getLocation()); }
}