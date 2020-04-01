package com.example.demo.dao;

import java.util.List;

import com.example.demo.dto.ResProDto;

public class ProjectDao {
	private int project_id;
	private String name;
	private int allocated_capital;
	private int used_capital;
	List<ResProDto> resources;

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAllocated_capital() {
		return allocated_capital;
	}

	public void setAllocated_capital(int allocated_capital) {
		this.allocated_capital = allocated_capital;
	}

	public int getUsed_capital() {
		return used_capital;
	}

	public void setUsed_capital(int used_capital) {
		this.used_capital = used_capital;
	}

	public List<ResProDto> getResources() {
		return resources;
	}

	public void setResources(List<ResProDto> resources) {
		this.resources = resources;
	}

	public ProjectDao() {
		super();
	}

}
