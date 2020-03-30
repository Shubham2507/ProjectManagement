package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.demo.entity.Designation;
import com.example.demo.repo.DesignationRepo;
import com.example.demo.response.ResponseData;
import com.example.demo.service.IDesignatonService;

@RestController("DesignationController")
@RequestMapping("/poc/designation")
@EnableWebMvc
public class DesignationController {
	@Autowired
	private IDesignatonService designatonService;
	@Autowired
	private DesignationRepo designationRepo;
	
	
	String msg = "Following Data Found";
	
	@GetMapping
	public ResponseData getDesignation() {
		
	

		List<Designation> designation = designatonService.getAllDesignation();
		
		return new ResponseData("200", msg, designation);

}
	// add method
	
	@PostMapping("/add")
	public ResponseData addItem(@RequestBody Designation designation) {


		String designations = designatonService.addItemToDesignation(designation);
		
		return new ResponseData("200", msg, designations);

	}
	






}
