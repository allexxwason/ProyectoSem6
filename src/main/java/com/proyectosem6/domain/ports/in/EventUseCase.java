package com.proyectosem6.domain.ports.in;

import com.proyectosem6.domain.model.Event;
import com.proyectosem6.domain.model.EventFilter;
import com.proyectosem6.domain.model.Pagination;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface EventUseCase {
    Page<Event> findAll(Pagination pagination, EventFilter filter);
    Optional<Event> getEventById(Long id);
    Event createEvent(Event event);
    Optional<Event> updateEvent(Long id, Event event);
    boolean deleteEvent(Long id);
}
