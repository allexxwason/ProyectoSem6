package com.proyectosem6.application.usecase;

import com.proyectosem6.domain.model.Event;
import com.proyectosem6.domain.model.EventFilter;
import com.proyectosem6.domain.model.Pagination;
import com.proyectosem6.domain.ports.out.EventRepositoryPort;
import com.proyectosem6.exception.ConflictException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventUseCaseImplTest {

	private EventRepositoryPort repo;
	private EventUseCaseImpl useCase;

	@BeforeEach
	void setUp() {
		repo = Mockito.mock(EventRepositoryPort.class);
		useCase = new EventUseCaseImpl(repo);
	}

	@Test
	void createEvent_success() {
		Event in = new Event(null, "Concert", "2025-10-01", "Arena", "Music", "City", LocalDate.now().plusDays(5));
		when(repo.existsByName("Concert")).thenReturn(false);
		when(repo.save(in)).thenReturn(new Event(1L, in.getName(), in.getDate(), in.getVenue(), in.getCategory(), in.getCity(), in.getStartDate()));

		Event out = useCase.createEvent(in);
		assertNotNull(out);
		assertEquals(1L, out.getId());
		verify(repo).save(in);
	}

	@Test
	void createEvent_conflict_throws() {
		Event in = new Event(null, "Duplicate", "2025-10-10", "Arena", "Music", "City", LocalDate.now().plusDays(10));
		when(repo.existsByName("Duplicate")).thenReturn(true);
		assertThrows(ConflictException.class, () -> useCase.createEvent(in));
		verify(repo, never()).save(any());
	}

	@Test
	void updateEvent_notFound_returnsEmpty() {
		when(repo.findById(99L)).thenReturn(Optional.empty());
		Optional<Event> res = useCase.updateEvent(99L, new Event());
		assertTrue(res.isEmpty());
	}

	@Test
	void deleteEvent_delegates() {
		when(repo.deleteById(2L)).thenReturn(true);
		assertTrue(useCase.deleteEvent(2L));
		verify(repo).deleteById(2L);
	}

	@Test
	void findAll_returnsPage() {
		Pagination p = new Pagination(0, 10, "id,asc");
		EventFilter f = new EventFilter(null, null, null);
		Page<Event> page = new PageImpl<>(List.of(new Event(1L, "E", "2025-10-01", "V", "C", "City", LocalDate.now().plusDays(1))));
		when(repo.findAll(p, f)).thenReturn(page);
		Page<Event> result = useCase.findAll(p, f);
		assertEquals(1, result.getTotalElements());
	}
}
