package com.example.demo.dao;

import java.util.List;

import com.example.demo.dto.ResProDto;

public class ResourcesDao {
	private int resource_id;
	private String name;
	private String designation;
	List<ResProDto> project;

	public List<ResProDto> getProject() {
		return project;
	}

	public void setProject(List<ResProDto> project) {
		this.project = project;
	}

	public int getResource_id() {
		return resource_id;
	}

	public void setResource_id(int resource_id) {
		this.resource_id = resource_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public ResourcesDao() {
		super();
	}

}
