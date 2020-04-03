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

import com.example.demo.dao.ProjectDao;

import com.example.demo.entity.Projects;

import com.example.demo.response.ResponseData;
import com.example.demo.service.IProjectService;

@RestController("ProjectController")
@RequestMapping("/poc/project")
@EnableWebMvc
public class ProjectController {
	@Autowired
	private IProjectService projectService;

	// method to get all projects
	@GetMapping
	public ResponseData getProject() {

		List<ProjectDao> projectDao = projectService.getAllProjects();
		String msg = "Following Data Found";

		return new ResponseData("200", msg, projectDao);

	}
	// add method

	@PostMapping("/add/{organizationId}")
	public ResponseData addItem(@PathVariable(value = "organizationId") int organizatonId,
			@RequestBody Projects projects) {

		Projects obj = projectService.addItemToProjects(organizatonId, projects);
		String msg = "Resorce Added Successfully!!";
		return new ResponseData("200", msg, obj);

	}

	// method to update Designation
	@PutMapping
	public ResponseData updateProject(@RequestBody Projects projects) {
		Projects projects2 = projectService.updateProjects(projects);
		String msg = "Designation Updation Successfully";
		return new ResponseData("200", msg, projects2);
	}

	// method to delete Designation
	@DeleteMapping(value = "/{id}")
	public String deleteOneDesignation(@PathVariable("id") int Id) {

		projectService.deleteOneProject(Id);
		return "Deletion Successful of cartId= " + Id;
	}

}
