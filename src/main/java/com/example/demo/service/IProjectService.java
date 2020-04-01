package com.example.demo.service;

import java.util.List;

import com.example.demo.dao.ProjectDao;
import com.example.demo.entity.Projects;

public interface IProjectService {

	List<ProjectDao> getAllProjects();

	String deleteOneProject(int projectId);

	Projects addItemToProjects(int id,Projects projectsObj);

	Projects updateProjects(Projects projects);

}
