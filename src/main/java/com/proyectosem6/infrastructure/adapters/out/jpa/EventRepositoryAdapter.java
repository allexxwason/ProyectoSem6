package com.proyectosem6.infrastructure.adapters.out.jpa;

import com.proyectosem6.domain.model.Event;
import com.proyectosem6.domain.model.EventFilter;
import com.proyectosem6.domain.model.Pagination;
import com.proyectosem6.domain.ports.out.EventRepositoryPort;
import com.proyectosem6.entity.EventEntity;
import com.proyectosem6.infrastructure.config.mapper.EventMapper;
import com.proyectosem6.repository.EventRepository;
import com.proyectosem6.repository.EventSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventRepositoryAdapter implements EventRepositoryPort {

	private final EventRepository repo;
	private final EventMapper mapper;

	public EventRepositoryAdapter(EventRepository repo, EventMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

	@Override
	public Page<Event> findAll(Pagination pagination, EventFilter filter) {
		String[] parts = (pagination.getSort() != null) ? pagination.getSort().split(",") : new String[]{"id", "asc"};
		Sort.Direction dir = parts.length > 1 && "desc".equalsIgnoreCase(parts[1]) ? Sort.Direction.DESC : Sort.Direction.ASC;
		PageRequest pr = PageRequest.of(pagination.getPage(), pagination.getSize(), Sort.by(dir, parts[0]));
		Specification<EventEntity> spec = EventSpecification.filterBy(filter.getCity(), filter.getCategory(), filter.getStartDate());
		return repo.findAll(spec, pr).map(mapper::toDomain);
	}

	@Override
	public Optional<Event> findById(Long id) {
		return repo.findById(id).map(mapper::toDomain);
	}

	@Override
	public Event save(Event event) {
		EventEntity saved = repo.save(mapper.toEntity(event));
		return mapper.toDomain(saved);
	}

	@Override
	public boolean existsByName(String name) {
		return repo.existsByName(name);
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
