package com.proyectosem6.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

public class EventDTO {
    private Long id;

    @NotBlank(message = "El nombre del evento es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String name;

    @NotBlank(message = "La fecha del evento es obligatoria")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "La fecha debe estar en formato YYYY-MM-DD")
    private String date;

    @NotBlank(message = "El venue es obligatorio")
    @Size(min = 3, max = 100, message = "El venue debe tener entre 3 y 100 caracteres")
    private String venue;

    @NotBlank(message = "La categoría es obligatoria")
    @Size(min = 3, max = 50, message = "La categoría debe tener entre 3 y 50 caracteres")
    private String category;

    public EventDTO() {}

    public EventDTO(Long id, String name, String date, String venue, String category) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.venue = venue;
        this.category = category;
    }

    // Constructor adicional para tests
    public EventDTO(Long id, String name, String date, String venue) {
        this(id, name, date, venue, null);
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
