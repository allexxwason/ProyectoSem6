package com.proyectosem6.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "events", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false, length = 100)
    private String venue;

    @Column(nullable = false, length = 50)
    private String category;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false)
    private LocalDate startDate;

    public EventEntity() {}

    public EventEntity(String name, String date, String venue, String category, String city, LocalDate startDate) {
        this.name = name;
        this.date = date;
        this.venue = venue;
        this.category = category;
        this.city = city;
        this.startDate = startDate;
    }

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
