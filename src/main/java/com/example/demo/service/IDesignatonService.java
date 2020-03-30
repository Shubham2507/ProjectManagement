package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Designation;



public interface IDesignatonService {
	List<Designation> getAllDesignation();

	
    String deleteOneDesignation(int designationId);
	  
	  
	String addItemToDesignation(Designation designationObj);


	
	  
	String updateDesignation(Designation designation);
	  
	  //String deleteAllDesignation();
	 

}
