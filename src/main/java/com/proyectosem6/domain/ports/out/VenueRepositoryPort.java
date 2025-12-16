package com.proyectosem6.domain.ports.out;

import com.proyectosem6.domain.model.Venue;

import java.util.List;
import java.util.Optional;

public interface VenueRepositoryPort {
    List<Venue> findAll();
    Optional<Venue> findById(Long id);
    Venue save(Venue venue);
    boolean existsByNameIgnoreCase(String name);
    boolean deleteById(Long id);
}
