package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Designation;

public interface IDesignatonService {
	List<Designation> getAllDesignation();

	String deleteOneDesignation(int designationId);

	Designation addItemToDesignation(Designation designationObj);

	Designation updateDesignation(Designation designation);

}
