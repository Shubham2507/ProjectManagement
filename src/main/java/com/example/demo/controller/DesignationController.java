package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.demo.entity.Designation;

import com.example.demo.response.ResponseData;
import com.example.demo.service.IDesignatonService;

@RestController("DesignationController")
@RequestMapping("/poc/designation")
@EnableWebMvc
public class DesignationController {
	@Autowired
	private IDesignatonService designatonService;

	// method to get all designations
	@GetMapping
	public ResponseData getDesignation() {

		List<Designation> designation = designatonService.getAllDesignation();
		String msg = "Following Data Found";

		return new ResponseData("200", msg, designation);

	}
	// add method

	@PostMapping("/add")
	public ResponseData addDesignation(@RequestBody Designation designation) {

		Designation designations = designatonService.addItemToDesignation(designation);
		String msg = "Data Added Successfully";
		return new ResponseData("200", msg, designations);

	}

	// method to update Designation
	@PutMapping
	public ResponseData updateDsignation(@RequestBody Designation designation) {
		Object designations = designatonService.updateDesignation(designation);
		String msg = "Designation Updation Successfully";
		return new ResponseData("200", msg, designations);
	}

	// method to delete Designation
	@DeleteMapping(value = "/{id}")
	public String deleteOneDesignation(@PathVariable("id") int Id) {

		designatonService.deleteOneDesignation(Id);
		return "Deletion Successful of cartId= " + Id;
	}

}
