package com.proyectosem6.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Future;
import java.time.LocalDate;

public class EventDTO {
    private Long id;
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String name;
    @NotBlank(message = "La fecha es obligatoria")
    @Future(message = "La fecha debe ser futura")
    private LocalDate date;
    @NotBlank(message = "El venue es obligatorio")
    private String venue;
    public EventDTO() {}
    public EventDTO(Long id, String name, LocalDate date, String venue) {
        this.id = id; this.name = name; this.date = date; this.venue = venue;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }
}