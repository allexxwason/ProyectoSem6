package com.proyectosem6.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectosem6.dto.EventDTO;
import com.proyectosem6.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper mapper;
    @MockBean EventService service;

    @Test
    void shouldCreateEventAndReturnOk() throws Exception {
        EventDTO created = new EventDTO(1L, "Concierto", "2025-12-01", "Arena");
        when(service.createEvent(any(EventDTO.class))).thenReturn(created);

        EventDTO payload = new EventDTO(null, "Concierto", "2025-12-01", "Arena");

        mvc.perform(post("/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(payload)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Concierto"));
    }

    @Test
    void shouldReturnBadRequestWhenNameBlank() throws Exception {
        EventDTO payload = new EventDTO(null, "", "2025-12-01", "Arena");

        mvc.perform(post("/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(payload)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void shouldGetEventById() throws Exception {
        EventDTO e = new EventDTO(1L, "Concierto", "2025-12-01", "Arena");
        when(service.getEventById(1L)).thenReturn(Optional.of(e));

        mvc.perform(get("/events/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Concierto"));
    }

    @Test
    void shouldUpdateEvent() throws Exception {
        EventDTO updated = new EventDTO(1L, "Concierto Updated", "2025-12-02", "Arena");
    when(service.updateEvent(eq(1L), any(EventDTO.class))).thenReturn(Optional.of(updated));

        EventDTO payload = new EventDTO(null, "Concierto Updated", "2025-12-02", "Arena");

        mvc.perform(put("/events/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(payload)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Concierto Updated"));
    }

    @Test
    void shouldDeleteEvent() throws Exception {
        when(service.deleteEvent(1L)).thenReturn(true);

        mvc.perform(delete("/events/1"))
            .andExpect(status().isNoContent());
    }
}
