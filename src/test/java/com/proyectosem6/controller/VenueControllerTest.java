package com.proyectosem6.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectosem6.dto.VenueDTO;
import com.proyectosem6.domain.model.Venue;
import com.proyectosem6.domain.ports.in.VenueUseCase;
import com.proyectosem6.infrastructure.config.mapper.VenueMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = VenueController.class)
class VenueControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private VenueUseCase venueUseCase;

	@MockBean
	private VenueMapper venueMapper;

	@Test
	void get_all_ok() throws Exception {
		Venue v = new Venue(1L, "Hall", "Centro");
		VenueDTO dto = new VenueDTO(1L, "Hall", "Centro");
		when(venueUseCase.getAllVenues()).thenReturn(List.of(v));
		when(venueMapper.toDto(v)).thenReturn(dto);
		mvc.perform(get("/venues"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1));
	}

	@Test
	void post_create_ok() throws Exception {
		VenueDTO dto = new VenueDTO(null, "Sala", "Centro");
		Venue in = new Venue(null, "Sala", "Centro");
		Venue saved = new Venue(1L, "Sala", "Centro");
		VenueDTO out = new VenueDTO(1L, "Sala", "Centro");

		when(venueMapper.toDomain(any(VenueDTO.class))).thenReturn(in);
		when(venueUseCase.createVenue(any(Venue.class))).thenReturn(saved);
		when(venueMapper.toDto(saved)).thenReturn(out);

		mvc.perform(post("/venues")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1));
	}

	@Test
	void get_by_id_notFound() throws Exception {
		when(venueUseCase.getVenueById(1L)).thenReturn(Optional.empty());
		mvc.perform(get("/venues/1"))
				.andExpect(status().isNotFound());
	}

	@Test
	void post_badRequest_missingName() throws Exception {
		String body = "{\"location\":\"Centro\"}";
		mvc.perform(post("/venues")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
				.andExpect(status().isBadRequest());
	}

	@Test
	void delete_notFound() throws Exception {
		when(venueUseCase.deleteVenue(10L)).thenReturn(false);
		mvc.perform(delete("/venues/10"))
				.andExpect(status().isNotFound());
	}

	@Test
	void delete_ok() throws Exception {
		when(venueUseCase.deleteVenue(1L)).thenReturn(true);
		mvc.perform(delete("/venues/1"))
				.andExpect(status().isNoContent());
	}
}
