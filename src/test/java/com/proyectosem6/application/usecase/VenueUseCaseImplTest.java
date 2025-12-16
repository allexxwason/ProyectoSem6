package com.proyectosem6.application.usecase;

import com.proyectosem6.domain.model.Venue;
import com.proyectosem6.domain.ports.out.VenueRepositoryPort;
import com.proyectosem6.exception.ConflictException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VenueUseCaseImplTest {

	private VenueRepositoryPort repo;
	private VenueUseCaseImpl useCase;

	@BeforeEach
	void setUp() {
		repo = Mockito.mock(VenueRepositoryPort.class);
		useCase = new VenueUseCaseImpl(repo);
	}

	@Test
	void createVenue_success() {
		Venue in = new Venue(null, "MainHall", "Centro");
		when(repo.existsByNameIgnoreCase("MainHall")).thenReturn(false);
		when(repo.save(in)).thenReturn(new Venue(1L, "MainHall", "Centro"));

		Venue out = useCase.createVenue(in);
		assertNotNull(out);
		assertEquals(1L, out.getId());
		verify(repo).save(in);
	}

	@Test
	void createVenue_conflict() {
		Venue in = new Venue(null, "Dup", "Lugar");
		when(repo.existsByNameIgnoreCase("Dup")).thenReturn(true);
		assertThrows(ConflictException.class, () -> useCase.createVenue(in));
		verify(repo, never()).save(any());
	}

	@Test
	void getAll_and_delete() {
		when(repo.findAll()).thenReturn(List.of(new Venue(1L, "V1", "L1")));
		assertFalse(useCase.getAllVenues().isEmpty());
		when(repo.deleteById(1L)).thenReturn(true);
		assertTrue(useCase.deleteVenue(1L));
	}

	@Test
	void update_notFound_returnsEmpty() {
		when(repo.findById(99L)).thenReturn(Optional.empty());
		Optional<Venue> res = useCase.updateVenue(99L, new Venue());
		assertTrue(res.isEmpty());
	}
}
