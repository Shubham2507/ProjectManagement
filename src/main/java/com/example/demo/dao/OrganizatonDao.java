package com.example.demo.dao;

import java.util.List;

import com.example.demo.dto.OrgProDto;

public class OrganizatonDao {
	private int id;
	private String name;
	private int total_cost;
	private List<OrgProDto> projects;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(int total_cost) {
		this.total_cost = total_cost;
	}

	public List<OrgProDto> getProjects() {
		return projects;
	}

	public void setProjects(List<OrgProDto> projects) {
		this.projects = projects;
	}

	public OrganizatonDao() {
		super();
	}

}
