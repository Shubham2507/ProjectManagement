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

import com.example.demo.dao.OrganizatonDao;

import com.example.demo.entity.Organization;
import com.example.demo.response.ResponseData;
import com.example.demo.service.IOrganizationService;;

@RestController("OrganizationController")
@RequestMapping("/poc/organization")
@EnableWebMvc
public class OrganizationController {
	@Autowired
	private IOrganizationService IOrganizationService;

	// method to get all organizations
	@GetMapping
	public ResponseData getOrganization() {
		List<OrganizatonDao> daos = IOrganizationService.getAllOrganization();
		String msg = "Following Data Found";
		return new ResponseData("200", msg, daos);

	}
	// add method

	@PostMapping("/add")
	public ResponseData addItem(@RequestBody Organization organizationObj) {

		Organization obj = IOrganizationService.addItemToOrganization(organizationObj);
		String msg = "Organization Added Successfully!!";
		return new ResponseData("200", msg, obj);

	}

	// method to delete organzation
	@DeleteMapping(value = "/{id}")
	public String deleteOneOrganization(@PathVariable("id") int Id) {

		IOrganizationService.deleteOneOrganization(Id);
		return "Deletion Successful of cartId= " + Id;
	}

	// method to update organization
	@PutMapping
	public ResponseData updateOrganization(@RequestBody Organization organization) {
		Organization organization2 = IOrganizationService.updateOrganization(organization);
		String msg = "Designation Updation Successfully";
		return new ResponseData("200", msg, organization2);
	}

}
