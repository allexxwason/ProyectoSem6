package com.proyectosem6.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class VenueDTO {
    private Long id;
    @NotBlank(message = "El nombre del venue es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String name;
    @NotBlank(message = "La ubicación del venue es obligatoria")
    @Size(min = 3, max = 100, message = "La ubicación debe tener entre 3 y 100 caracteres")
    private String location;
    public VenueDTO() {}
    public VenueDTO(Long id, String name, String location) {
        this.id = id; this.name = name; this.location = location;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}