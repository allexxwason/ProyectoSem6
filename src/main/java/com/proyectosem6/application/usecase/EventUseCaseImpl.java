package com.proyectosem6.application.usecase;

import com.proyectosem6.domain.ports.in.EventUseCase;
import com.proyectosem6.domain.ports.out.EventRepositoryPort;
import com.proyectosem6.domain.model.Event;
import com.proyectosem6.domain.model.Pagination;
import com.proyectosem6.domain.model.EventFilter;
import com.proyectosem6.exception.ConflictException;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.util.Optional;

@Service
public class EventUseCaseImpl implements EventUseCase {

	private final EventRepositoryPort repository;

	public EventUseCaseImpl(EventRepositoryPort repository) {
		this.repository = repository;
	}

	@Override
	public Page<Event> findAll(Pagination pagination, EventFilter filter) {
		return repository.findAll(pagination, filter);
	}

	@Override
	public Optional<Event> getEventById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Event createEvent(Event event) {
		if (event.getName() != null && repository.existsByName(event.getName())) {
			throw new ConflictException("Evento ya existe con el mismo nombre");
		}
		return repository.save(event);
	}

	@Override
	public Optional<Event> updateEvent(Long id, Event event) {
		Optional<Event> existing = repository.findById(id);
		if (existing.isEmpty()) return Optional.empty();
		event.setId(id);
		return Optional.of(repository.save(event));
	}

	@Override
	public boolean deleteEvent(Long id) {
		return repository.deleteById(id);
	}
}
