package com.proyectosem6.entity;

import jakarta.persistence.*;

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

    public EventEntity() {}

    public EventEntity(String name, String date, String venue, String category) {
        this.name = name;
        this.date = date;
        this.venue = venue;
        this.category = category;
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
}
