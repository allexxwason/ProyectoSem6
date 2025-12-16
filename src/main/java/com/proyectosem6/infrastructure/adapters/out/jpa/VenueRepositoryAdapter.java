package com.proyectosem6.infrastructure.adapters.out.jpa;

import com.proyectosem6.domain.model.Venue;
import com.proyectosem6.domain.ports.out.VenueRepositoryPort;
import com.proyectosem6.entity.VenueEntity;
import com.proyectosem6.infrastructure.config.mapper.VenueMapper;
import com.proyectosem6.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VenueRepositoryAdapter implements VenueRepositoryPort {

	private final VenueRepository repo;
	private final VenueMapper mapper;

	public VenueRepositoryAdapter(VenueRepository repo, VenueMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

	@Override
	public List<Venue> findAll() {
		return repo.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
	}

	@Override
	public Optional<Venue> findById(Long id) {
		return repo.findById(id).map(mapper::toDomain);
	}

	@Override
	public Venue save(Venue venue) {
		VenueEntity saved = repo.save(mapper.toEntity(venue));
		return mapper.toDomain(saved);
	}

	@Override
	public boolean existsByNameIgnoreCase(String name) {
		return repo.findByNameIgnoreCase(name).isPresent();
	}

	@Override
	public boolean deleteById(Long id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}
}
