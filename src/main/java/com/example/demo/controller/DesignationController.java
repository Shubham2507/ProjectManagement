package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
		/*if(cart==null)
			throw new ItemNotFoundInCartException("Cart is Empty");*/
		return new ResponseData("200", msg, designation);

}}
