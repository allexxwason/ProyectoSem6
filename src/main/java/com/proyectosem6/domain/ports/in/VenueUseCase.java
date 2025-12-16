package com.proyectosem6.domain.ports.in;

import com.proyectosem6.domain.model.Venue;

import java.util.List;
import java.util.Optional;

public interface VenueUseCase {
    List<Venue> getAllVenues();
    Optional<Venue> getVenueById(Long id);
    Venue createVenue(Venue venue);
    Optional<Venue> updateVenue(Long id, Venue venue);
    boolean deleteVenue(Long id);
}
