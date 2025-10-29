package com.proyectosem6.dto;

public class EventDTO {
    private Long id;
    private String name;
    private String date;
    private String venue;

    public EventDTO() {}

    public EventDTO(Long id, String name, String date, String venue) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.venue = venue;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }
}
