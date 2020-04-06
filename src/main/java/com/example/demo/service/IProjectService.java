package com.example.demo.service;

import java.util.List;

import com.example.demo.dao.ProjectDao;
import com.example.demo.entity.Projects;

public interface IProjectService {
	List<Projects> getAll();
  
	List<ProjectDao> getAllProjects();

	String deleteOneProject(int projectId);

	Object addItemToProjects(int id,Projects projectsObj);

	Object updateProjects(Projects projects);

}
