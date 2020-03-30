package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.demo.entity.Designation;
import com.example.demo.entity.Resources;
import com.example.demo.response.ResponseData;
import com.example.demo.service.IResourcesService;;

@RestController("ResourcesController")
@RequestMapping("/poc/resources")
@EnableWebMvc
public class ResourcesController {
	@Autowired
	private IResourcesService resourcesService;
	
	String msg = "Following Data Found";
	
	// add method
	
		@PostMapping("/add/{designationId}")
		public ResponseData addItem(@PathVariable(value = "designationId") int designationId,@RequestBody Resources resources) {


			String obj = resourcesService.addItemToResources(designationId, resources);
			
			return new ResponseData("200", msg, obj);


}}
