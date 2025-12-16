package com.proyectosem6.domain.model;

import java.time.LocalDate;

public class EventFilter {
	private String city;
	private String category;
	private LocalDate startDate;

	public EventFilter() {}

	public EventFilter(String city, String category, LocalDate startDate) {
		this.city = city;
		this.category = category;
		this.startDate = startDate;
	}

	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }
	public String getCategory() { return category; }
	public void setCategory(String category) { this.category = category; }
	public LocalDate getStartDate() { return startDate; }
	public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
}
