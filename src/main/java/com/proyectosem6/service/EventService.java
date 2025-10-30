package com.proyectosem6.service;
import com.proyectosem6.dto.EventDTO;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class EventService {
    private final List<EventDTO> events = new ArrayList<>();
    private Long idCounter = 1L;
    public List<EventDTO> getAllEvents() { return events; }
    public Optional<EventDTO> getEventById(Long id) { return events.stream().filter(e -> e.getId().equals(id)).findFirst(); }
    public EventDTO createEvent(EventDTO event) { event.setId(idCounter++); events.add(event); return event; }
    public Optional<EventDTO> updateEvent(Long id, EventDTO updated) {
        return getEventById(id).map(e -> { e.setName(updated.getName()); e.setDate(updated.getDate()); e.setVenue(updated.getVenue()); return e; });
    }
    public boolean deleteEvent(Long id) { return events.removeIf(e -> e.getId().equals(id)); }
}