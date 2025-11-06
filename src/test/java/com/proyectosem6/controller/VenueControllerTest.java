package com.proyectosem6.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectosem6.dto.VenueDTO;
import com.proyectosem6.service.VenueService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VenueController.class)
public class VenueControllerTest {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper mapper;
    @MockBean VenueService service;

    @Test
    void shouldCreateVenueAndReturnOk() throws Exception {
        VenueDTO created = new VenueDTO(1L, "Sala Principal", "Centro");
        when(service.createVenue(any(VenueDTO.class))).thenReturn(created);

        VenueDTO payload = new VenueDTO(null, "Sala Principal", "Centro");

        mvc.perform(post("/venues")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(payload)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Sala Principal"));
    }

    @Test
    void shouldReturnBadRequestWhenNameBlank() throws Exception {
        VenueDTO payload = new VenueDTO(null, "", "Centro");

        mvc.perform(post("/venues")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(payload)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void shouldGetVenueById() throws Exception {
        VenueDTO v = new VenueDTO(1L, "Sala Principal", "Centro");
        when(service.getVenueById(1L)).thenReturn(Optional.of(v));

        mvc.perform(get("/venues/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Sala Principal"));
    }
}
