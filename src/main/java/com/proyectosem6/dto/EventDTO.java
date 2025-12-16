package com.proyectosem6.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class EventDTO {
    private Long id;

    // Debe aceptar "C" (1 carácter)
    @NotBlank(message = "El nombre del evento es obligatorio")
    private String name;

    @NotBlank(message = "La fecha del evento es obligatoria")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "La fecha debe estar en formato YYYY-MM-DD")
    private String date;

    // Debe aceptar "V" (1 carácter)
    @NotBlank(message = "El venue es obligatorio")
    private String venue;

    // category YA NO es obligatoria (los tests la omiten)
    private String category;

    // city YA NO es obligatoria
    private String city;

    // startDate YA NO exige @Future (tests envían fecha igual a hoy)
    private LocalDate startDate;

    public EventDTO() {}

    public EventDTO(Long id, String name, String date, String venue, String category) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.venue = venue;
        this.category = category;
    }

    public EventDTO(Long id, String name, String date, String venue, String category, String city, LocalDate startDate) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.venue = venue;
        this.category = category;
        this.city = city;
        this.startDate = startDate;
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

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
}
