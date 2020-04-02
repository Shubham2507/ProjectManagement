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

import com.example.demo.dao.ResourcesDao;
import com.example.demo.entity.Designation;
import com.example.demo.entity.Resources;
import com.example.demo.response.ResponseData;
import com.example.demo.service.IResourcesService;

@RestController("ResourcesController")
@RequestMapping("/poc/resources")
@EnableWebMvc
public class ResourcesController {
	@Autowired
	private IResourcesService resourcesService;

	String msg = "Following Data Found";

	// method to get all designations
	@GetMapping
	public ResponseData getDesignation() {

		List<ResourcesDao> output = resourcesService.getAllResources();

		return new ResponseData("200", msg, output);

	}

	// add method

	@PostMapping("/add/{designationId}")
	public ResponseData addItem(@PathVariable(value = "designationId") int designationId,
			@RequestBody Resources resources) {

		Resources obj = resourcesService.addItemToResources(designationId, resources);
		msg = "Resorce Added Successfully!!";
		return new ResponseData("200", msg, obj);

	}

	// method to delete reosurce
	@DeleteMapping(value = "/{id}")
	public String deleteOneResorce(@PathVariable("id") int Id) {

		resourcesService.deleteOneDesignation(Id);
		return "Deletion Successful of cartId= " + Id;
	}

	// method to update Resource
	@PutMapping
	public ResponseData updateResource(@RequestBody Resources resources) {
		Resources resources2 = resourcesService.updateResources(resources);
		msg = "Designation Updation Successfully";
		return new ResponseData("200", msg, resources2);
	}
}
