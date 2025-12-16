package com.proyectosem6.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "venues", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class VenueEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(nullable = false, length = 100)
	private String location;

	public VenueEntity() {}

	public VenueEntity(String name, String location) {
		this.name = name;
		this.location = location;
	}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getLocation() { return location; }
	public void setLocation(String location) { this.location = location; }
}
