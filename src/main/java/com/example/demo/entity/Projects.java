package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "Pojects")
public class Projects {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int project_id;
	private String name;
	private int allocated_capital;
	private int used_capital;
	
	@ManyToOne
    @JoinColumn(name="organization_id", nullable=false)
	private Organization organization;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Project_Resource", 
        joinColumns = { @JoinColumn(name = "project_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "resource_id") }
    )
    Set<Resources> resources = new HashSet<>();
	
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
	public Set<Resources> getResources() {
		return resources;
	}
	public void setResources(Set<Resources> resources) {
		this.resources = resources;
	}
	
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	
	public Projects(int project_id, String name, int allocated_capital, int used_capital, Organization organization,
			Set<Resources> resources) {
		super();
		this.project_id = project_id;
		this.name = name;
		this.allocated_capital = allocated_capital;
		this.used_capital = used_capital;
		this.organization = organization;
		this.resources = resources;
	}
	public Projects() {
		super();
	}
	
	

}
