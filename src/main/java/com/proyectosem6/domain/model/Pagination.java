package com.proyectosem6.domain.model;

public class Pagination {
	private int page;
	private int size;
	private String sort; // e.g. "id,asc"

	public Pagination() {}

	public Pagination(int page, int size, String sort) {
		this.page = page;
		this.size = size;
		this.sort = sort;
	}

	public int getPage() { return page; }
	public void setPage(int page) { this.page = page; }
	public int getSize() { return size; }
	public void setSize(int size) { this.size = size; }
	public String getSort() { return sort; }
	public void setSort(String sort) { this.sort = sort; }
}
