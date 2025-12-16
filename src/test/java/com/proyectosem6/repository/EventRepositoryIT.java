package com.proyectosem6.repository;

import com.proyectosem6.entity.EventEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EventRepositoryIT {

    @Autowired
    private EventRepository eventRepository;

    @Test
    void save_and_findById() {
        EventEntity e = new EventEntity("IT Event", "2025-11-11", "Arena", "Music", "City", LocalDate.of(2025,11,11));
        EventEntity saved = eventRepository.save(e);
        Optional<EventEntity> found = eventRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("IT Event", found.get().getName());
    }

    @Test
    void existsByName_works() {
        EventEntity e = new EventEntity("UniqueName", "2025-11-12", "Arena", "Music", "City", LocalDate.of(2025,11,12));
        eventRepository.save(e);
        assertTrue(eventRepository.existsByName("UniqueName"));
    }
}
