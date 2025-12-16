package com.proyectosem6.domain.model;

import java.time.LocalDate;

public class Event {
    private Long id;
    private String name;
    private String date;
    private String venue;
    private String category;
    private String city;
    private LocalDate startDate;

    public Event() {}

    public Event(Long id, String name, String date, String venue, String category, String city, LocalDate startDate) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.venue = venue;
        this.category = category;
        this.city = city;
        this.startDate = startDate;
    }

    // Getters / setters
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
