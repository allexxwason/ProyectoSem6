package com.proyectosem6.application.usecase;

import com.proyectosem6.domain.ports.in.VenueUseCase;
import com.proyectosem6.domain.ports.out.VenueRepositoryPort;
import com.proyectosem6.domain.model.Venue;
import com.proyectosem6.exception.ConflictException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenueUseCaseImpl implements VenueUseCase {

	private final VenueRepositoryPort repository;

	public VenueUseCaseImpl(VenueRepositoryPort repository) {
		this.repository = repository;
	}

	@Override
	public List<Venue> getAllVenues() {
		return repository.findAll();
	}

	@Override
	public Optional<Venue> getVenueById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Venue createVenue(Venue venue) {
		if (venue.getName() != null && repository.existsByNameIgnoreCase(venue.getName())) {
			throw new ConflictException("Venue ya existe con el mismo nombre");
		}
		return repository.save(venue);
	}

	@Override
	public Optional<Venue> updateVenue(Long id, Venue venue) {
		Optional<Venue> existing = repository.findById(id);
		if (existing.isEmpty()) return Optional.empty();
		venue.setId(id);
		return Optional.of(repository.save(venue));
	}

	@Override
	public boolean deleteVenue(Long id) {
		return repository.deleteById(id);
	}
}
