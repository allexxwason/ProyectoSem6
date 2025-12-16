package com.proyectosem6.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectosem6.dto.EventDTO;
import com.proyectosem6.domain.model.Event;
import com.proyectosem6.domain.ports.in.EventUseCase;
import com.proyectosem6.infrastructure.config.mapper.EventMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = EventController.class)
class EventControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private EventUseCase useCase;

    @MockBean
    private EventMapper eventMapper;

    @Test
    void post_create_ok() throws Exception {
        EventDTO inDto = new EventDTO(null, "C", "2025-12-01", "V", "Cat", "City", LocalDate.of(2025, 12, 1));
        Event domainIn = new Event(null, "C", "2025-12-01", "V", "Cat", "City", LocalDate.of(2025, 12, 1));
        Event created = new Event(1L, "C", "2025-12-01", "V", "Cat", "City", LocalDate.of(2025, 12, 1));
        EventDTO outDto = new EventDTO(1L, "C", "2025-12-01", "V", "Cat", "City", LocalDate.of(2025, 12, 1));

        when(eventMapper.toDomain(any(EventDTO.class))).thenReturn(domainIn);
        when(useCase.createEvent(any(Event.class))).thenReturn(created);
        when(eventMapper.toDto(created)).thenReturn(outDto);

        mvc.perform(post("/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(inDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void getAllEvents() throws Exception {
        Event domainEvent = new Event(1L, "Concierto", "2025-12-01",
                "V", "Cat", "City", LocalDate.now());

        EventDTO dto = new EventDTO(1L, "Concierto", "2025-12-01",
                "V", "Cat", "City", LocalDate.now());

        Mockito.when(useCase.findAll(any(), any()))
                .thenReturn(new org.springframework.data.domain.PageImpl<>(List.of(domainEvent)));

        Mockito.when(eventMapper.toDto(any(Event.class))).thenReturn(dto);

        mvc.perform(get("/events"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Concierto"));
    }

    @Test
    void get_by_id_notFound() throws Exception {
        when(useCase.getEventById(1L)).thenReturn(Optional.empty());
        mvc.perform(get("/events/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void get_by_id_found() throws Exception {
        Event e = new Event(1L, "Event", "2025-12-01", "V", "Cat", "City", LocalDate.of(2025, 12, 1));
        EventDTO dto = new EventDTO(1L, "Event", "2025-12-01", "V", "Cat", "City", LocalDate.of(2025, 12, 1));

        when(useCase.getEventById(1L)).thenReturn(Optional.of(e));
        when(eventMapper.toDto(e)).thenReturn(dto);

        mvc.perform(get("/events/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void shouldUpdateEvent() throws Exception {
        EventDTO payload = new EventDTO(null, "Concierto Updated", "2025-12-02", "Arena");
        Event domainIn = new Event(null, "Concierto Updated", "2025-12-02", "Arena", null, null, null);
        Event updatedDomain = new Event(1L, "Concierto Updated", "2025-12-02", "Arena", null, null, null);
        EventDTO outDto = new EventDTO(1L, "Concierto Updated", "2025-12-02", "Arena");

        when(eventMapper.toDomain(any(EventDTO.class))).thenReturn(domainIn);
        when(useCase.updateEvent(eq(1L), any(Event.class))).thenReturn(Optional.of(updatedDomain));
        when(eventMapper.toDto(updatedDomain)).thenReturn(outDto);

        mvc.perform(put("/events/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Concierto Updated"));
    }

    @Test
    void put_update_notFound() throws Exception {
        EventDTO dto = new EventDTO(null, "X", "2025-12-01", "V", "Cat", "City", LocalDate.of(2025, 12, 1));

        when(eventMapper.toDomain(any(EventDTO.class))).thenReturn(new Event());
        when(useCase.updateEvent(eq(99L), any(Event.class))).thenReturn(Optional.empty());

        mvc.perform(put("/events/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_notFound() throws Exception {
        when(useCase.deleteEvent(5L)).thenReturn(false);
        mvc.perform(delete("/events/5"))
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_ok() throws Exception {
        when(useCase.deleteEvent(1L)).thenReturn(true);
        mvc.perform(delete("/events/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void post_badRequest_validationFails() throws Exception {
        String body = "{\"date\":\"2025-12-01\",\"venue\":\"V\"}"; // faltan campos obligatorios

        mvc.perform(post("/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isBadRequest());
    }
}
