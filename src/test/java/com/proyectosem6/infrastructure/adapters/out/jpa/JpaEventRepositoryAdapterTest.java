package com.proyectosem6.infrastructure.adapters.out.jpa;

import com.proyectosem6.domain.model.Event;
import com.proyectosem6.domain.model.EventFilter;
import com.proyectosem6.domain.model.Pagination;
import com.proyectosem6.entity.EventEntity;
import com.proyectosem6.infrastructure.config.mapper.EventMapper;
import com.proyectosem6.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({EventRepositoryAdapter.class, EventMapper.class})
class JpaEventRepositoryAdapterTest {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventRepositoryAdapter adapter;

    @Test
    void save_and_find_via_adapter() {
        Event domain = new Event(null, "ITEvent", "2025-11-11", "Arena", "Music", "City", LocalDate.of(2025, 11, 11));
        Event saved = adapter.save(domain);
        assertNotNull(saved.getId());
        Optional<Event> found = adapter.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("ITEvent", found.get().getName());
    }

    @Test
    void delete_via_adapter_returns_true() {
        EventEntity e = eventRepository.save(new EventEntity("ToDelete", "2025-11-12", "V", "C", "City", LocalDate.of(2025, 11, 12)));
        boolean deleted = adapter.deleteById(e.getId());
        assertTrue(deleted);
        assertFalse(eventRepository.findById(e.getId()).isPresent());
    }

    @Test
    void delete_nonexistent_returns_false() {
        boolean deleted = adapter.deleteById(9999L);
        assertFalse(deleted);
    }

    @Test
    void exists_by_name() {
        eventRepository.save(new EventEntity("UniqueName", "2025-11-13", "V", "C", "City", LocalDate.of(2025, 11, 13)));
        assertTrue(adapter.existsByName("UniqueName"));
        assertFalse(adapter.existsByName("NonExistent"));
    }

    @Test
    void findAll_with_pagination() {
        eventRepository.save(new EventEntity("E1", "2025-11-14", "V", "C", "City", LocalDate.of(2025, 11, 14)));
        Pagination p = new Pagination(0, 10, "id,asc");
        EventFilter f = new EventFilter(null, null, null);
        var page = adapter.findAll(p, f);
        assertTrue(page.getTotalElements() >= 1);
    }
}
