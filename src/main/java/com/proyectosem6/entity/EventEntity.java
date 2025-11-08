package com.proyectosem6.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "events", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private LocalDate date;

    private String venue;

    public EventEntity() {}

    public EventEntity(String name, LocalDate date, String venue) {
        this.name = name;
        this.date = date;
        this.venue = venue;
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
