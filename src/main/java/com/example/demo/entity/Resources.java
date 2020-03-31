package com.example.demo.entity;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Resources")
public class Resources implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int resource_id;
	private String name;
	@ManyToOne
    @JoinColumn(name="designation_id", nullable=false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Designation designation;
	
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


	public Designation getDesignation() {
		return designation;
	}


	public void setDesignation(Designation designation) {
		this.designation = designation;
	}
	
	public Resources(int resource_id, String name, Designation designation) {
		super();
		this.resource_id = resource_id;
		this.name = name;
		this.designation = designation;
		
	}


	public Resources() {
		super();
	} 
	

}
