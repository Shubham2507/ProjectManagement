package com.example.demo.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Organization")
public class Organization {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private int total_cost;
	
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

	

	public Organization(int id, String name, int total_cost) {
		super();
		this.id = id;
		this.name = name;
		this.total_cost = total_cost;
		
	}

	public Organization() {
		super();
	}
	
	

}
