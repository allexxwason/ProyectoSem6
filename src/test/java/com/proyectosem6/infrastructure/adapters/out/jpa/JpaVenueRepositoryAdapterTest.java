package com.proyectosem6.infrastructure.adapters.out.jpa;

import com.proyectosem6.domain.model.Venue;
import com.proyectosem6.entity.VenueEntity;
import com.proyectosem6.infrastructure.config.mapper.VenueMapper;
import com.proyectosem6.repository.VenueRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({VenueRepositoryAdapter.class, VenueMapper.class})
class JpaVenueRepositoryAdapterTest {

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private VenueRepositoryAdapter adapter;

    @Test
    void save_and_find() {
        Venue v = new Venue(null, "SalaIT", "Centro");
        Venue saved = adapter.save(v);
        assertNotNull(saved.getId());
        Optional<Venue> found = adapter.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("SalaIT", found.get().getName());
    }

    @Test
    void delete_returns_true() {
        VenueEntity e = venueRepository.save(new VenueEntity("Vdel", "Loc"));
        boolean deleted = adapter.deleteById(e.getId());
        assertTrue(deleted);
        assertFalse(venueRepository.findById(e.getId()).isPresent());
    }

    @Test
    void delete_nonexistent_returns_false() {
        boolean deleted = adapter.deleteById(9999L);
        assertFalse(deleted);
    }

    @Test
    void existsByNameIgnoreCase() {
        venueRepository.save(new VenueEntity("CaseTest", "Loc"));
        assertTrue(adapter.existsByNameIgnoreCase("casetest"));
        assertFalse(adapter.existsByNameIgnoreCase("NonExist"));
    }

    @Test
    void findAll_returns_list() {
        venueRepository.save(new VenueEntity("V1", "L1"));
        var list = adapter.findAll();
        assertTrue(list.size() >= 1);
    }
}
