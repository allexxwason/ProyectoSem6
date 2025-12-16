package com.proyectosem6.domain.ports.out;

import com.proyectosem6.domain.model.Event;
import com.proyectosem6.domain.model.EventFilter;
import com.proyectosem6.domain.model.Pagination;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface EventRepositoryPort {
    Page<Event> findAll(Pagination pagination, EventFilter filter);
    Optional<Event> findById(Long id);
    Event save(Event event);
    boolean existsByName(String name);
    boolean deleteById(Long id);
}
